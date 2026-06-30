#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "PjsipNative"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

static JavaVM *g_jvm = nullptr;
static jobject g_callback_obj = nullptr;

extern "C" {

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
    g_jvm = vm;
    LOGI("PJSIP JNI_OnLoad");
    return JNI_VERSION_1_6;
}

static void notifyJavaEvent(const char *eventType, const char *callId, int statusCode, const char *reason) {
    if (!g_jvm || !g_callback_obj) return;

    JNIEnv *env;
    bool attached = false;
    jint status = g_jvm->GetEnv((void **) &env, JNI_VERSION_1_6);

    if (status == JNI_EDETACHED) {
        if (g_jvm->AttachCurrentThread(&env, nullptr) == JNI_OK) {
            attached = true;
        } else {
            return;
        }
    }

    jclass cls = env->GetObjectClass(g_callback_obj);
    jmethodID mid = env->GetMethodID(cls, "onNativeEvent", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V");

    jstring jEventType = env->NewStringUTF(eventType);
    jstring jCallId = env->NewStringUTF(callId);
    jstring jReason = env->NewStringUTF(reason);

    env->CallVoidMethod(g_callback_obj, mid, jEventType, jCallId, statusCode, jReason);

    env->DeleteLocalRef(jEventType);
    env->DeleteLocalRef(jCallId);
    env->DeleteLocalRef(jReason);

    if (attached) {
        g_jvm->DetachCurrentThread();
    }
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeInit(JNIEnv *env, jobject thiz, jint logLevel) {
    LOGI("PJSIP nativeInit: logLevel=%d", logLevel);
    g_callback_obj = env->NewGlobalRef(thiz);
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeDestroy(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeDestroy");
    if (g_callback_obj) {
        env->DeleteGlobalRef(g_callback_obj);
        g_callback_obj = nullptr;
    }
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeRegister(
        JNIEnv *env, jobject thiz,
        jstring accountId, jstring registrarUri, jstring username, jstring password,
        jstring realm, jstring proxyUri, jboolean iceEnabled) {

    const char *cAccountId = env->GetStringUTFChars(accountId, nullptr);
    const char *cRegistrarUri = env->GetStringUTFChars(registrarUri, nullptr);
    const char *cUsername = env->GetStringUTFChars(username, nullptr);
    const char *cPassword = env->GetStringUTFChars(password, nullptr);

    LOGI("PJSIP nativeRegister: user=%s registrar=%s", cUsername, cRegistrarUri);

    // TODO: Integrate with actual PJSIP pjsua2 library
    // This is the placeholder that will call pjsua2 Account::modify()
    // For now, simulate successful registration
    notifyJavaEvent("on_registration_state", "", 200, "OK");

    env->ReleaseStringUTFChars(accountId, cAccountId);
    env->ReleaseStringUTFChars(registrarUri, cRegistrarUri);
    env->ReleaseStringUTFChars(username, cUsername);
    env->ReleaseStringUTFChars(password, cPassword);

    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeUnregister(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeUnregister");
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeMakeCall(JNIEnv *env, jobject thiz, jstring targetUri) {
    const char *cTargetUri = env->GetStringUTFChars(targetUri, nullptr);
    LOGI("PJSIP nativeMakeCall: %s", cTargetUri);

    // TODO: Integrate with actual PJSIP pjsua2 Call::makeCall()
    notifyJavaEvent("on_call_state", "outgoing", 180, "Ringing");

    env->ReleaseStringUTFChars(targetUri, cTargetUri);
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeHangup(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeHangup");
    // TODO: Call currentCall.hangup(prm)
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeHold(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeHold");
    // TODO: Call currentCall.setHold(prm)
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeResume(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeResume");
    // TODO: Call currentCall.reinvite(prm)
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeTransfer(JNIEnv *env, jobject thiz, jstring targetUri) {
    const char *cTargetUri = env->GetStringUTFChars(targetUri, nullptr);
    LOGI("PJSIP nativeTransfer: %s", cTargetUri);
    // TODO: Call currentCall.xfer(targetUri, prm)
    env->ReleaseStringUTFChars(targetUri, cTargetUri);
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeAnswer(JNIEnv *env, jobject thiz) {
    LOGI("PJSIP nativeAnswer");
    // TODO: Call currentCall.answer(prm)
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeMute(JNIEnv *env, jobject thiz, jboolean mute) {
    LOGI("PJSIP nativeMute: %d", mute);
    // TODO: Adjust audio media direction (RX only for mute)
    return 0;
}

JNIEXPORT jint JNICALL
Java_com_crm_v2_telephony_pjsip_PjsipManager_nativeSetSpeaker(JNIEnv *env, jobject thiz, jboolean on) {
    LOGI("PJSIP nativeSetSpeaker: %d", on);
    // TODO: Route audio to speaker via AudioManager
    return 0;
}

} // extern "C"
