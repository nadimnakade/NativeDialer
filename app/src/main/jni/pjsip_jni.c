#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <pjsua-lib/pjsua.h>
#include <pjlib-util.h>
#include <pjlib.h>
#include "pjsip_wss_transport.h"

#define LOG_TAG "PjsipBridge"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

static JavaVM *g_jvm = NULL;
static jobject g_callback_obj = NULL;
static jmethodID g_on_reg_state = NULL;
static jmethodID g_on_call_state = NULL;
static jmethodID g_on_incoming_call = NULL;
static jmethodID g_on_call_media_state = NULL;

static pjsua_acc_id g_acc_id = -1;
static int g_transport_type = 0;  /* 0=udp, 1=tls, 2=wss */
static pjsip_transport *g_wss_transport = NULL;
static pjsua_transport_id g_wss_transport_id = -1;
static char g_wss_server[256] = "";
static int g_wss_port = 0;

extern JavaVM *pj_jni_jvm;

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    g_jvm = vm;
    pj_jni_jvm = vm;
    LOGI("JNI_OnLoad called");
    return JNI_VERSION_1_6;
}

static void get_jni_env(JNIEnv **env) {
    int status = (*g_jvm)->GetEnv(g_jvm, (void **)env, JNI_VERSION_1_6);
    if (status == JNI_EDETACHED) {
        (*g_jvm)->AttachCurrentThread(g_jvm, env, NULL);
    }
}

static void on_reg_state_cb(pjsua_acc_id acc_id) {
    JNIEnv *env;
    get_jni_env(&env);
    if (env && g_callback_obj) {
        pjsua_acc_info info;
        int code = 0;
        char reason[256] = "";
        if (pjsua_acc_get_info(acc_id, &info) == PJ_SUCCESS) {
            code = info.status;
            pj_str_t reason_str = info.status_text;
            int len = PJ_MIN((int)reason_str.slen, (int)sizeof(reason)-1);
            memcpy(reason, reason_str.ptr, len);
            reason[len] = '\0';
        }
        (*env)->CallVoidMethod(env, g_callback_obj, g_on_reg_state, acc_id, code, (*env)->NewStringUTF(env, reason));
        LOGI("on_reg_state: acc=%d code=%d reason=%s", acc_id, code, reason);
    }
}

static void on_call_state_cb(pjsua_call_id call_id, pjsip_event *e) {
    JNIEnv *env;
    get_jni_env(&env);
    if (env && g_callback_obj) {
        pjsua_call_info info;
        pj_status_t status = pjsua_call_get_info(call_id, &info);
        int state = (status == PJ_SUCCESS) ? info.state : -1;
        int last_status = (status == PJ_SUCCESS) ? (int)info.last_status : 0;
        char reason[256] = "";
        if (status == PJ_SUCCESS) {
            int len = PJ_MIN((int)info.last_status_text.slen, (int)sizeof(reason)-1);
            memcpy(reason, info.last_status_text.ptr, len);
            reason[len] = '\0';
        }
        (*env)->CallVoidMethod(env, g_callback_obj, g_on_call_state, call_id, state, last_status, (*env)->NewStringUTF(env, reason));
        LOGI("on_call_state: call=%d, state=%d, last_status=%d, reason=%s", call_id, state, last_status, reason);
    }
}

static void on_incoming_call_cb(pjsua_acc_id acc_id, pjsua_call_id call_id, pjsip_rx_data *rdata) {
    JNIEnv *env;
    get_jni_env(&env);
    if (env && g_callback_obj) {
        pjsua_call_info info;
        char remote_uri[512] = "";
        if (pjsua_call_get_info(call_id, &info) == PJ_SUCCESS) {
            int len = PJ_MIN((int)info.remote_info.slen, (int)sizeof(remote_uri)-1);
            memcpy(remote_uri, info.remote_info.ptr, len);
            remote_uri[len] = '\0';
        }
        (*env)->CallVoidMethod(env, g_callback_obj, g_on_incoming_call, acc_id, call_id, (*env)->NewStringUTF(env, remote_uri));
        LOGI("on_incoming_call: acc=%d, call=%d, remote=%s", acc_id, call_id, remote_uri);
    }
}

static void on_call_media_state_cb(pjsua_call_id call_id) {
    JNIEnv *env;
    get_jni_env(&env);
    LOGI("on_call_media_state: call=%d", call_id);

    pjsua_call_info info;
    if (pjsua_call_get_info(call_id, &info) == PJ_SUCCESS) {
        LOGI("  media_status=%d, media_cnt=%d", info.media_status, info.media_cnt);

        if (info.media_status == PJSUA_CALL_MEDIA_ACTIVE) {
            /* Connect call audio to the sound device */
            pjsua_conf_connect(info.conf_slot, 0);
            pjsua_conf_connect(0, info.conf_slot);
            pjsua_conf_adjust_rx_level(0, 1.0);
            pjsua_conf_adjust_tx_level(0, 1.0);
            LOGI("Media ACTIVE for call %d — audio connected to sound device", call_id);
        }
    }

    if (env && g_callback_obj) {
        (*env)->CallVoidMethod(env, g_callback_obj, g_on_call_media_state, call_id);
    }
}

/* ---- Transport creation helpers ---- */

static pj_status_t create_udp_transport(void) {
    pjsua_transport_config cfg;
    pjsua_transport_config_default(&cfg);
    cfg.port = 0;
    return pjsua_transport_create(PJSIP_TRANSPORT_UDP, &cfg, NULL);
}

static pj_status_t create_tcp_transport(void) {
    pjsua_transport_config cfg;
    pjsua_transport_config_default(&cfg);
    cfg.port = 0;
    return pjsua_transport_create(PJSIP_TRANSPORT_TCP, &cfg, NULL);
}

static pj_status_t create_tls_transport(void) {
    pjsua_transport_config cfg;
    pjsua_transport_config_default(&cfg);
    cfg.port = 0;
    cfg.tls_setting.method = PJSIP_TLSV1_2_METHOD;
    cfg.tls_setting.verify_server = PJ_FALSE;
    cfg.tls_setting.verify_client = PJ_FALSE;
    return pjsua_transport_create(PJSIP_TRANSPORT_TLS, &cfg, NULL);
}

static pj_status_t create_wss_transport(const char *server, int port, const char *path) {
    if (!server) {
        LOGE("WSS: no server provided");
        return PJ_EINVAL;
    }

    pjsip_endpoint *endpt = pjsua_get_pjsip_endpt();
    if (!endpt) {
        LOGE("WSS: pjsip endpoint not available");
        return PJ_EINVALIDOP;
    }

    pj_status_t status = pjsip_wss_create(endpt, server, port, path ? path : "/",
                                            &g_wss_transport);
    if (status != PJ_SUCCESS) {
        LOGE("WSS transport create failed: %d", status);
        return status;
    }

    status = pjsua_transport_register(g_wss_transport, &g_wss_transport_id);
    if (status != PJ_SUCCESS) {
        LOGE("WSS pjsua transport register failed: %d", status);
        pjsip_wss_stop(g_wss_transport);
        g_wss_transport = NULL;
        return status;
    }

    pj_ansi_strxcpy(g_wss_server, server, sizeof(g_wss_server));
    g_wss_port = port;

    LOGI("WSS transport created and registered: id=%d for %s:%d%s",
         g_wss_transport_id, server, port, path ? path : "/");
    return PJ_SUCCESS;
}

/* ---- Android logger for PJSIP ---- */
static void android_log_func(int level, const char *data, int len) {
    int prio;
    switch (level) {
        case 1: case 2: prio = ANDROID_LOG_ERROR; break;
        case 3: case 4: prio = ANDROID_LOG_WARN; break;
        case 5: case 6: prio = ANDROID_LOG_INFO; break;
        default: prio = ANDROID_LOG_DEBUG; break;
    }
    __android_log_print(prio, "PjsipLib", "%.*s", len, data);
}

/* ---- JNI entry points ---- */

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeCreate(JNIEnv *env, jobject thiz) {
    pjsua_config cfg;
    pjsua_logging_config log_cfg;
    pjsua_media_config media_cfg;
    pj_status_t status;

    pjsua_config_default(&cfg);
    pjsua_logging_config_default(&log_cfg);
    pjsua_media_config_default(&media_cfg);

    log_cfg.level = 3;
    log_cfg.console_level = 4;
    log_cfg.decor = PJ_LOG_HAS_NEWLINE | PJ_LOG_HAS_SENDER;
    log_cfg.cb = android_log_func;

    /* Enable floating point for better audio quality */
    media_cfg.has_ioqueue = PJ_TRUE;
    media_cfg.clock_rate = 8000;
    media_cfg.audio_frame_ptime = 20;
    media_cfg.ec_options = 0;
    media_cfg.ec_tail_len = 200;
    media_cfg.no_vad = PJ_TRUE;

    /* No STUN - keep it simple for now */

    /* Disable SRTP - FreeSWITCH UDP may not support it */
    cfg.use_srtp = PJMEDIA_SRTP_DISABLED;
    cfg.srtp_secure_signaling = 0;

    /* No DTLS-SRTP */
    cfg.srtp_opt.keying_count = 0;

    cfg.cb.on_reg_state = on_reg_state_cb;
    cfg.cb.on_call_state = on_call_state_cb;
    cfg.cb.on_incoming_call = on_incoming_call_cb;
    cfg.cb.on_call_media_state = on_call_media_state_cb;
    cfg.thread_cnt = 4;

    status = pjsua_create();
    if (status != PJ_SUCCESS) {
        LOGE("pjsua_create failed: %d", status);
        return (jint)status;
    }

    status = pjsua_init(&cfg, &log_cfg, &media_cfg);
    if (status != PJ_SUCCESS) {
        LOGE("pjsua_init failed: %d", status);
        return (jint)status;
    }

    status = pjsua_start();
    if (status != PJ_SUCCESS) {
        LOGE("pjsua_start failed: %d", status);
        return (jint)status;
    }

    /* ---- Configure codec priorities ---- */
    /* Only enable standard codecs that FreeSWITCH accepts:
       PCMU (0), PCMA (8), and telephone-event (101) */
    {
        unsigned count = 128;
        pjsua_codec_info codec_info[128];
        unsigned i;
        status = pjsua_enum_codecs(codec_info, &count);
        if (status == PJ_SUCCESS) {
            for (i = 0; i < count; i++) {
                char codec_id[64];
                int len = PJ_MIN((int)codec_info[i].codec_id.slen, (int)sizeof(codec_id)-1);
                memcpy(codec_id, codec_info[i].codec_id.ptr, len);
                codec_id[len] = '\0';

                /* Keep PCMU (0), PCMA (8), telephone-event (101) */
                if (pj_stricmp2(&codec_info[i].codec_id, "PCMU/8000/1") == 0 ||
                    pj_stricmp2(&codec_info[i].codec_id, "PCMA/8000/1") == 0 ||
                    pj_stricmp2(&codec_info[i].codec_id, "telephone-event/8000/1") == 0) {
                    pjsua_codec_set_priority(&codec_info[i].codec_id, 200 - (unsigned)i);
                    LOGI("Codec ENABLED: %s (priority high)", codec_id);
                } else {
                    pjsua_codec_set_priority(&codec_info[i].codec_id, 0);
                    LOGI("Codec disabled: %s", codec_id);
                }
            }
        }
    }

    LOGI("pjsua created and started successfully (codecs restricted to PCMU/PCMA)");
    return 0;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeAddTransport(JNIEnv *env, jobject thiz, jint transport_type, jstring jserver, jint port) {
    pj_status_t status;
    g_transport_type = transport_type;

    const char *server = jserver ? (*env)->GetStringUTFChars(env, jserver, NULL) : NULL;

    switch (transport_type) {
    case 0:
        status = create_udp_transport();
        LOGI("UDP transport: %d", status);
        break;
    case 1:
        status = create_tls_transport();
        LOGI("TLS transport: %d", status);
        break;
    case 2:
        status = create_wss_transport(server ? server : "sip2.cloud-connect.in", port, "/");
        LOGI("WSS transport: %d", status);
        break;
    case 3:
        status = create_tcp_transport();
        LOGI("TCP transport: %d", status);
        break;
    default:
        status = PJ_EINVAL;
        break;
    }

    if (server) (*env)->ReleaseStringUTFChars(env, jserver, server);
    return (jint)status;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeAddAccount(
        JNIEnv *env, jobject thiz, jstring juri, jstring jreguri, jstring jusername, jstring jpassword, jstring jrealm) {
    const char *uri = (*env)->GetStringUTFChars(env, juri, NULL);
    const char *reguri = (*env)->GetStringUTFChars(env, jreguri, NULL);
    const char *username = (*env)->GetStringUTFChars(env, jusername, NULL);
    const char *password = (*env)->GetStringUTFChars(env, jpassword, NULL);
    const char *realm = (*env)->GetStringUTFChars(env, jrealm, NULL);

    pjsua_acc_config cfg;
    pjsua_acc_config_default(&cfg);

    cfg.id = pj_str((char *)uri);
    cfg.reg_uri = pj_str((char *)reguri);

    cfg.use_srtp = PJMEDIA_SRTP_DISABLED;
    cfg.srtp_secure_signaling = 0;

    cfg.cred_count = 1;
    cfg.cred_info[0].realm = pj_str((char *)realm);
    cfg.cred_info[0].scheme = pj_str("digest");
    cfg.cred_info[0].username = pj_str((char *)username);
    cfg.cred_info[0].data_type = PJSIP_CRED_DATA_PLAIN_PASSWD;
    cfg.cred_info[0].data = pj_str((char *)password);
    cfg.register_on_acc_add = PJ_TRUE;

    cfg.reg_timeout = 3600;
    cfg.reg_first_retry_interval = 10;
    cfg.reg_retry_interval = 60;

    pjsua_acc_id acc_id;
    pj_status_t status = pjsua_acc_add(&cfg, PJ_TRUE, &acc_id);
    if (status == PJ_SUCCESS) {
        g_acc_id = acc_id;
    }

    (*env)->ReleaseStringUTFChars(env, juri, uri);
    (*env)->ReleaseStringUTFChars(env, jreguri, reguri);
    (*env)->ReleaseStringUTFChars(env, jusername, username);
    (*env)->ReleaseStringUTFChars(env, jpassword, password);
    (*env)->ReleaseStringUTFChars(env, jrealm, realm);

    LOGI("Add account result: status=%d, acc_id=%d, reg_uri=%s", status, acc_id, reguri);
    return (jint)status;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeMakeCall(
        JNIEnv *env, jobject thiz, jstring juri) {
    const char *uri = (*env)->GetStringUTFChars(env, juri, NULL);
    pj_str_t dst_uri = pj_str((char *)uri);

    pjsua_call_id call_id;
    pj_status_t status = pjsua_call_make_call(g_acc_id >= 0 ? g_acc_id : 0,
                                               &dst_uri, NULL, NULL, NULL, &call_id);

    (*env)->ReleaseStringUTFChars(env, juri, uri);

    LOGI("Make call result: status=%d, call_id=%d", status, call_id);
    return (jint)status;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeHangupCall(
        JNIEnv *env, jobject thiz, jint call_id) {
    pj_status_t status = pjsua_call_hangup(call_id, 0, NULL, NULL);
    LOGI("Hangup call: call_id=%d, status=%d", call_id, status);
    return (jint)status;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeGetActiveCallId(JNIEnv *env, jobject thiz) {
    pjsua_call_id call_ids[8];
    unsigned count = 8;
    pj_status_t status = pjsua_enum_calls(call_ids, &count);
    if (status != PJ_SUCCESS || count == 0) {
        LOGI("Get active call: no active calls (status=%d, count=%u)", status, count);
        return (jint)-1;
    }
    int active_id = (int)call_ids[0];
    LOGI("Get active call: call_id=%d (count=%u)", active_id, count);
    return (jint)active_id;
}

JNIEXPORT void JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeSetCallback(
        JNIEnv *env, jobject thiz, jobject callback) {
    if (g_callback_obj) {
        (*env)->DeleteGlobalRef(env, g_callback_obj);
    }
    g_callback_obj = (*env)->NewGlobalRef(env, callback);

    jclass cls = (*env)->GetObjectClass(env, callback);
    g_on_reg_state = (*env)->GetMethodID(env, cls, "onRegState", "(IILjava/lang/String;)V");
    g_on_call_state = (*env)->GetMethodID(env, cls, "onCallState", "(IIILjava/lang/String;)V");
    g_on_incoming_call = (*env)->GetMethodID(env, cls, "onIncomingCall", "(IILjava/lang/String;)V");
    g_on_call_media_state = (*env)->GetMethodID(env, cls, "onCallMediaState", "(I)V");

    LOGI("Callback set");
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeAnswerCall(
        JNIEnv *env, jobject thiz, jint call_id, jint code) {
    pj_status_t status = pjsua_call_answer(call_id, code, NULL, NULL);
    LOGI("Answer call: call_id=%d, code=%d, status=%d", call_id, code, status);
    return (jint)status;
}

JNIEXPORT jint JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeSetMute(
        JNIEnv *env, jobject thiz, jint call_id, jboolean mute) {
    pjsua_call_info info;
    pj_status_t status = pjsua_call_get_info(call_id, &info);
    if (status != PJ_SUCCESS) {
        LOGE("Set mute: call_get_info failed: %d", status);
        return (jint)status;
    }

    if (info.conf_slot > 0) {
        if (mute) {
            status = pjsua_conf_adjust_tx_level(info.conf_slot, 0.0);
            LOGI("Mute ON: call_id=%d, conf_slot=%d, tx_level=0, status=%d", call_id, info.conf_slot, status);
        } else {
            status = pjsua_conf_adjust_tx_level(info.conf_slot, 1.0);
            LOGI("Mute OFF: call_id=%d, conf_slot=%d, tx_level=1, status=%d", call_id, info.conf_slot, status);
        }
    } else {
        LOGI("Set mute: no conf_slot for call_id=%d", call_id);
    }

    return (jint)status;
}

JNIEXPORT void JNICALL Java_com_crm_v2_telephony_PjsipManager_nativeDestroy(JNIEnv *env, jobject thiz) {
    g_wss_transport = NULL;
    g_wss_transport_id = -1;
    g_acc_id = -1;
    pjsua_destroy();
    LOGI("pjsua destroyed");
}