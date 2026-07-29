#include "pjsip_wss_transport.h"
#include <pjsip/sip_endpoint.h>
#include <pjsip/sip_errno.h>
#include <pj/pool.h>
#include <pj/os.h>
#include <pj/string.h>
#include <pj/lock.h>
#include <pj/assert.h>
#include <pj/rand.h>
#include <pj/sock.h>
#include <pj/compat/socket.h>
#include <pjlib-util/base64.h>
#include <pjlib-util/sha1.h>

#include <mbedtls/net_sockets.h>
#include <mbedtls/ssl.h>
#include <mbedtls/ctr_drbg.h>
#include <mbedtls/entropy.h>
#include <mbedtls/error.h>
#include <errno.h>
#include <string.h>
#include <android/log.h>

#define LOG_TAG "WssTransport"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#define WSS_BUF_SIZE     65536

#define PJSIP_TRANSPORT_WSS  (PJSIP_TRANSPORT_START_OTHER + 1)

#define WS_OPCODE_CONTINUATION 0x0
#define WS_OPCODE_TEXT         0x1
#define WS_OPCODE_BINARY       0x2
#define WS_OPCODE_CLOSE        0x8
#define WS_OPCODE_PING         0x9
#define WS_OPCODE_PONG         0xA

struct pjsip_wss_transport
{
    pjsip_transport    base;
    pj_pool_t         *pool;
    pj_thread_t       *thread;
    pj_bool_t          thread_quit;
    pj_bool_t          connected;

    mbedtls_ssl_context      ssl;
    mbedtls_ssl_config       ssl_conf;
    mbedtls_net_context      net_fd;
    mbedtls_ctr_drbg_context ctr_drbg;
    mbedtls_entropy_context  entropy;

    char               host[256];
    int                port;
    char               path[256];

    char               recv_buf[WSS_BUF_SIZE];

    pj_lock_t         *send_lock;
};

static pj_status_t wss_send_msg(pjsip_transport *tp,
                                pjsip_tx_data *tdata,
                                const pj_sockaddr_t *rem_addr,
                                int addr_len,
                                void *token,
                                pjsip_transport_callback callback);
static pj_status_t wss_destroy(pjsip_transport *tp);

/* base64 encode */
static void ws_base64_encode(const unsigned char *input, int in_len,
                             char *output, int out_len)
{
    int actual_len = out_len;
    pj_base64_encode((const pj_uint8_t *)input, in_len, output, &actual_len);
    output[actual_len] = '\0';
}

/* ---- blocking TLS write ---- */
static pj_status_t tls_write_all(pjsip_wss_transport *wss,
                                 const void *data, int len)
{
    int ret;
    while ((ret = mbedtls_ssl_write(&wss->ssl, (const unsigned char *)data, len)) <= 0) {
        if (ret == MBEDTLS_ERR_SSL_WANT_WRITE || ret == MBEDTLS_ERR_SSL_WANT_READ)
            continue;
        return PJ_STATUS_FROM_OS(OSERR_ECONNRESET);
    }
    return PJ_SUCCESS;
}

/* ---- blocking TLS read (raw, may return less than len) ---- */
static int tls_read_some(pjsip_wss_transport *wss, void *buf, int len)
{
    int ret;
    while ((ret = mbedtls_ssl_read(&wss->ssl, (unsigned char *)buf, len)) <= 0) {
        if (ret == MBEDTLS_ERR_SSL_WANT_READ || ret == MBEDTLS_ERR_SSL_WANT_WRITE)
            continue;
        return -1;
    }
    return ret;
}

/* ---- blocking TLS read (exactly len bytes) ---- */
static int tls_read_exact(pjsip_wss_transport *wss, void *buf, int len)
{
    unsigned char *p = (unsigned char *)buf;
    int remaining = len;
    while (remaining > 0) {
        int n = tls_read_some(wss, p, remaining);
        if (n <= 0) return -1;
        p += n;
        remaining -= n;
    }
    return len;
}

/* ---- send HTTP Upgrade request ---- */
static pj_status_t ws_send_handshake(pjsip_wss_transport *wss)
{
    unsigned char rand_key[16];
    char key_b64[64];
    char request[1024];
    int i, len;

    for (i = 0; i < 16; i++)
        rand_key[i] = (unsigned char)(pj_rand() & 0xFF);

    ws_base64_encode(rand_key, 16, key_b64, sizeof(key_b64));

    len = pj_ansi_snprintf(request, sizeof(request),
        "GET %s HTTP/1.1\r\n"
        "Host: %s\r\n"
        "Upgrade: websocket\r\n"
        "Connection: Upgrade\r\n"
        "Sec-WebSocket-Key: %s\r\n"
        "Sec-WebSocket-Protocol: sip\r\n"
        "Sec-WebSocket-Version: 13\r\n"
        "\r\n",
        wss->path, wss->host, key_b64);

    LOGI("Sending WSS handshake:\n%s", request);
    return tls_write_all(wss, request, len);
}

/* ---- read HTTP 101 response ---- */
static pj_status_t ws_read_handshake_response(pjsip_wss_transport *wss)
{
    char buf[4096];
    int pos = 0, n;
    int header_end = -1;
    int attempts = 0;
    const int MAX_ATTEMPTS = 5000;  /* ~5 seconds at 1ms each */

    while (pos < (int)sizeof(buf) - 1 && attempts < MAX_ATTEMPTS) {
        n = tls_read_some(wss, buf + pos, 1);
        if (n <= 0) {
            if (wss->thread_quit) return PJ_STATUS_FROM_OS(OSERR_ECONNRESET);
            pj_thread_sleep(1);
            attempts++;
            continue;
        }
        attempts = 0;
        pos += n;
        buf[pos] = '\0';
        char *end = strstr(buf, "\r\n\r\n");
        if (end) { header_end = (int)(end - buf) + 4; break; }
    }

    if (attempts >= MAX_ATTEMPTS) {
        LOGE("Handshake response timeout (no data from server)");
        return PJ_STATUS_FROM_OS(OSERR_ECONNRESET);
    }

    /* Read a bit more in case there's a body with error details */
    for (int i = 0; i < 256 && pos < (int)sizeof(buf) - 1; i++) {
        n = tls_read_some(wss, buf + pos, 1);
        if (n <= 0) break;
        pos += n;
        buf[pos] = '\0';
    }

    LOGI("WSS handshake response (%d bytes):\n%s", pos, buf);

    if (!strstr(buf, "101")) {
        LOGE("WebSocket handshake rejected (no 101 in response)");
        return PJ_STATUS_FROM_OS(ECONNREFUSED);
    }
    return PJ_SUCCESS;
}

/* ---- send a WebSocket frame (client-side, MASK=1) ---- */
static pj_status_t ws_send_frame(pjsip_wss_transport *wss,
                                 int opcode,
                                 const void *payload,
                                 int payload_len)
{
    unsigned char header[14];
    int hdr_len, i;
    unsigned char mask_key[4];

    hdr_len = 0;
    header[hdr_len++] = (unsigned char)(0x80 | (opcode & 0x0F));
    header[hdr_len++] = 0x80;

    if (payload_len < 126) {
        header[1] |= (unsigned char)payload_len;
    } else if (payload_len < 65536) {
        header[1] |= 126;
        header[hdr_len++] = (unsigned char)((payload_len >> 8) & 0xFF);
        header[hdr_len++] = (unsigned char)(payload_len & 0xFF);
    } else {
        header[1] |= 127;
        int shift;
        for (shift = 56; shift >= 0; shift -= 8)
            header[hdr_len++] = (unsigned char)(((pj_uint64_t)payload_len >> shift) & 0xFF);
    }

    for (i = 0; i < 4; i++)
        mask_key[i] = (unsigned char)(pj_rand() & 0xFF);
    memcpy(header + hdr_len, mask_key, 4);
    hdr_len += 4;

    pj_lock_acquire(wss->send_lock);

    pj_status_t status = tls_write_all(wss, header, hdr_len);
    if (status == PJ_SUCCESS && payload_len > 0) {
        int use_heap = (payload_len > 4096);
        unsigned char *masked = use_heap ?
            (unsigned char *)malloc(payload_len) : NULL;
        unsigned char stack_buf[4096];
        unsigned char *buf = use_heap ? masked : stack_buf;

        if (!buf && use_heap) {
            pj_lock_release(wss->send_lock);
            return PJ_ENOMEM;
        }

        memcpy(buf, payload, payload_len);
        for (i = 0; i < payload_len; i++)
            buf[i] ^= mask_key[i & 3];

        status = tls_write_all(wss, buf, payload_len);
        if (use_heap) free(buf);
    }

    pj_lock_release(wss->send_lock);
    return status;
}

/* ---- read a WebSocket frame (blocking) ---- */
static int ws_read_frame(pjsip_wss_transport *wss,
                         int *opcode,
                         char *payload_buf,
                         int buf_size)
{
    unsigned char hdr[14];
    int n, hdr_len = 2, i;
    pj_uint64_t payload_len;
    int mask;

    n = tls_read_exact(wss, hdr, 2);
    if (n <= 0) return -1;

    *opcode = hdr[0] & 0x0F;
    mask = (hdr[1] & 0x80) ? 1 : 0;
    payload_len = hdr[1] & 0x7F;

    if (payload_len == 126) {
        n = tls_read_exact(wss, hdr + 2, 2);
        if (n <= 0) return -1;
        payload_len = ((pj_uint64_t)hdr[2] << 8) | hdr[3];
        hdr_len = 4;
    } else if (payload_len == 127) {
        n = tls_read_exact(wss, hdr + 2, 8);
        if (n <= 0) return -1;
        payload_len = 0;
        for (i = 0; i < 8; i++)
            payload_len = (payload_len << 8) | hdr[2 + i];
        hdr_len = 10;
    }

    unsigned char mask_key[4];
    if (mask) {
        n = tls_read_exact(wss, mask_key, 4);
        if (n <= 0) return -1;
        hdr_len += 4;
    }

    if (payload_len > (pj_uint64_t)buf_size) {
        LOGE("Frame too large: %lld > %d", (long long)payload_len, buf_size);
        return -1;
    }

    n = tls_read_exact(wss, payload_buf, (int)payload_len);
    if (n <= 0) return -1;

    if (mask) {
        for (i = 0; i < (int)payload_len; i++)
            payload_buf[i] ^= (char)mask_key[i & 3];
    }

    return (int)payload_len;
}

/* incoming packet list node */
struct recv_node
{
    PJ_DECL_LIST_MEMBER(struct recv_node);
    pjsip_rx_data rdata;
};

static void feed_to_pjsip(pjsip_wss_transport *wss,
                          const char *data, int len)
{
    pj_pool_t *pool;
    struct recv_node *node;
    int copy_len;

    pool = pjsip_endpt_create_pool(wss->base.endpt, "wssrd",
                                    PJSIP_POOL_RDATA_LEN,
                                    PJSIP_POOL_RDATA_INC + 5);
    if (!pool) return;

    node = PJ_POOL_ZALLOC_T(pool, struct recv_node);
    node->rdata.tp_info.pool = pool;
    node->rdata.tp_info.transport = &wss->base;

    copy_len = len < (int)sizeof(node->rdata.pkt_info.packet)
               ? len : (int)sizeof(node->rdata.pkt_info.packet);
    memcpy(node->rdata.pkt_info.packet, data, copy_len);
    node->rdata.pkt_info.len = copy_len;
    node->rdata.pkt_info.src_addr.addr.sa_family = pj_AF_INET();
    node->rdata.pkt_info.src_addr_len = sizeof(pj_sockaddr_in);
    pj_ansi_strxcpy(node->rdata.pkt_info.src_name, wss->host,
                    sizeof(node->rdata.pkt_info.src_name));
    node->rdata.pkt_info.src_port = wss->port;
    pj_gettimeofday(&node->rdata.pkt_info.timestamp);

    pjsip_tpmgr_receive_packet(wss->base.tpmgr, &node->rdata);
    pjsip_endpt_release_pool(wss->base.endpt, node->rdata.tp_info.pool);
}

/* ---- worker thread ---- */
static int wss_worker_thread(void *arg)
{
    pjsip_wss_transport *wss = (pjsip_wss_transport *)arg;
    char frame_buf[WSS_BUF_SIZE];

    LOGI("WSS worker thread started");

    while (!wss->thread_quit) {
        int opcode;
        int len = ws_read_frame(wss, &opcode, frame_buf, sizeof(frame_buf));
        if (len < 0) {
            if (wss->thread_quit) break;
            LOGE("WSS read error");
            pj_thread_sleep(3000);
            continue;
        }

        if (opcode == WS_OPCODE_CLOSE) {
            LOGI("WSS close frame received, length=%d", len);
            if (len >= 2) {
                unsigned short code = ((unsigned char)frame_buf[0] << 8) |
                                      (unsigned char)frame_buf[1];
                LOGI("Close code=%d", code);
            }
            continue;
        }

        if (opcode == WS_OPCODE_PING) {
            ws_send_frame(wss, WS_OPCODE_PONG, frame_buf, len);
            continue;
        }

        if (opcode == WS_OPCODE_PONG) {
            continue;
        }

        if (opcode == WS_OPCODE_BINARY || opcode == WS_OPCODE_TEXT) {
            feed_to_pjsip(wss, frame_buf, len);
        }
    }

    LOGI("WSS worker thread exiting");
    return 0;
}

/* ---- send_msg callback ---- */
static pj_status_t wss_send_msg(pjsip_transport *tp,
                                pjsip_tx_data *tdata,
                                const pj_sockaddr_t *rem_addr,
                                int addr_len,
                                void *token,
                                pjsip_transport_callback callback)
{
    pjsip_wss_transport *wss = (pjsip_wss_transport *)tp;
    PJ_UNUSED_ARG(rem_addr);
    PJ_UNUSED_ARG(addr_len);

    if (!wss->connected || wss->thread_quit) {
        return PJ_STATUS_FROM_OS(ENOTCONN);
    }

    pj_status_t status = pjsip_tx_data_encode(tdata);
    if (status != PJ_SUCCESS) return status;

    int len = (int)(tdata->buf.cur - tdata->buf.start);
    status = ws_send_frame(wss, WS_OPCODE_BINARY, tdata->buf.start, len);
    if (status != PJ_SUCCESS) return status;

    if (callback)
        (*callback)(tp, token, len);

    return PJ_SUCCESS;
}

/* ---- destroy callback ---- */
static pj_status_t wss_destroy(pjsip_transport *tp)
{
    pjsip_wss_transport *wss = (pjsip_wss_transport *)tp;

    wss->thread_quit = PJ_TRUE;
    wss->connected = PJ_FALSE;

    if (wss->thread) {
        pj_thread_join(wss->thread);
        pj_thread_destroy(wss->thread);
        wss->thread = NULL;
    }

    mbedtls_ssl_close_notify(&wss->ssl);
    mbedtls_net_free(&wss->net_fd);
    mbedtls_ssl_free(&wss->ssl);
    mbedtls_ssl_config_free(&wss->ssl_conf);
    mbedtls_ctr_drbg_free(&wss->ctr_drbg);
    mbedtls_entropy_free(&wss->entropy);

    if (wss->send_lock)
        pj_lock_destroy(wss->send_lock);
    if (wss->base.ref_cnt)
        pj_atomic_destroy(wss->base.ref_cnt);
    if (wss->base.lock)
        pj_lock_destroy(wss->base.lock);

    pjsip_endpt_release_pool(wss->base.endpt, wss->pool);
    return PJ_SUCCESS;
}

/* ---- connect (TLS handshake + WebSocket upgrade) ---- */
static pj_status_t wss_connect(pjsip_wss_transport *wss)
{
    int ret;
    char port_str[16], err_buf[256];

    mbedtls_net_init(&wss->net_fd);
    mbedtls_ssl_init(&wss->ssl);
    mbedtls_ssl_config_init(&wss->ssl_conf);
    mbedtls_ctr_drbg_init(&wss->ctr_drbg);
    mbedtls_entropy_init(&wss->entropy);

    mbedtls_ctr_drbg_seed(&wss->ctr_drbg, mbedtls_entropy_func,
                          &wss->entropy, NULL, 0);
    pj_ansi_snprintf(port_str, sizeof(port_str), "%d", wss->port);

    ret = mbedtls_net_connect(&wss->net_fd, wss->host, port_str,
                               MBEDTLS_NET_PROTO_TCP);
    if (ret != 0) {
        mbedtls_strerror(ret, err_buf, sizeof(err_buf));
        LOGE("TCP connect failed: -0x%x (%s)", -ret, err_buf);
        return PJ_STATUS_FROM_OS(ECONNREFUSED);
    }

    ret = mbedtls_ssl_config_defaults(&wss->ssl_conf, MBEDTLS_SSL_IS_CLIENT,
                                       MBEDTLS_SSL_TRANSPORT_STREAM,
                                       MBEDTLS_SSL_PRESET_DEFAULT);
    if (ret != 0) return PJ_STATUS_FROM_OS(ECONNREFUSED);

    mbedtls_ssl_conf_authmode(&wss->ssl_conf, MBEDTLS_SSL_VERIFY_NONE);
    mbedtls_ssl_conf_rng(&wss->ssl_conf, mbedtls_ctr_drbg_random, &wss->ctr_drbg);

    ret = mbedtls_ssl_setup(&wss->ssl, &wss->ssl_conf);
    if (ret != 0) return PJ_STATUS_FROM_OS(ECONNREFUSED);

    mbedtls_ssl_set_bio(&wss->ssl, &wss->net_fd,
                         mbedtls_net_send, mbedtls_net_recv, NULL);

    LOGI("TLS handshake to %s:%s...", wss->host, port_str);
    while ((ret = mbedtls_ssl_handshake(&wss->ssl)) != 0) {
        if (ret != MBEDTLS_ERR_SSL_WANT_READ &&
            ret != MBEDTLS_ERR_SSL_WANT_WRITE) {
            mbedtls_strerror(ret, err_buf, sizeof(err_buf));
            LOGE("TLS handshake failed: -0x%x (%s)", -ret, err_buf);
            return PJ_STATUS_FROM_OS(ECONNREFUSED);
        }
    }
    LOGI("TLS handshake OK");

    pj_status_t status = ws_send_handshake(wss);
    if (status != PJ_SUCCESS) return status;

    status = ws_read_handshake_response(wss);
    if (status != PJ_SUCCESS) return status;

    wss->connected = PJ_TRUE;
    LOGI("WSS connected to %s:%d%s", wss->host, wss->port, wss->path);
    return PJ_SUCCESS;
}

/* ---- public API ---- */
PJ_DEF(pj_status_t) pjsip_wss_create(pjsip_endpoint *endpt,
                                      const char *server,
                                      int port,
                                      const char *path,
                                      pjsip_transport **p_tp)
{
    pj_pool_t *pool;
    pjsip_wss_transport *wss;
    pj_status_t status;

    pool = pjsip_endpt_create_pool(endpt, "wss", 8000, 8000);
    if (!pool) return PJ_ENOMEM;

    wss = PJ_POOL_ZALLOC_T(pool, pjsip_wss_transport);
    wss->pool = pool;
    pj_ansi_snprintf(wss->base.obj_name, sizeof(wss->base.obj_name),
                     "wss%p", wss);
    wss->base.pool = pool;

    status = pj_atomic_create(pool, 0, &wss->base.ref_cnt);
    if (status != PJ_SUCCESS) goto on_error;

    status = pj_lock_create_recursive_mutex(pool, "wss_lock",
                                             &wss->base.lock);
    if (status != PJ_SUCCESS) goto on_error;

    status = pj_lock_create_simple_mutex(pool, "wss_send",
                                          &wss->send_lock);
    if (status != PJ_SUCCESS) goto on_error;

    wss->base.key.type = PJSIP_TRANSPORT_WSS;
    wss->base.type_name = "WSS";
    wss->base.info = "WSS (WebSocket Secure)";
    wss->base.flag = PJSIP_TRANSPORT_RELIABLE | PJSIP_TRANSPORT_SECURE;
    wss->base.local_name.host = pj_str("0.0.0.0");
    wss->base.local_name.port = 0;
    wss->base.addr_len = sizeof(pj_sockaddr_in);
    wss->base.dir = PJSIP_TP_DIR_OUTGOING;
    wss->base.endpt = endpt;
    wss->base.tpmgr = pjsip_endpt_get_tpmgr(endpt);
    wss->base.send_msg = &wss_send_msg;
    wss->base.do_shutdown = NULL;
    wss->base.destroy = &wss_destroy;

    pj_ansi_strxcpy(wss->host, server, sizeof(wss->host));
    wss->port = port;
    pj_ansi_strxcpy(wss->path, (path && path[0]) ? path : "/",
                    sizeof(wss->path));
    wss->base.remote_name.host = pj_str(wss->host);
    wss->base.remote_name.port = port;

    /* set remote address in key for transport manager lookup */
    pj_sockaddr_init(pj_AF_INET(), &wss->base.key.rem_addr,
                     &wss->base.remote_name.host,
                     (pj_uint16_t)wss->port);

    status = pjsip_transport_register(wss->base.tpmgr, &wss->base);
    if (status != PJ_SUCCESS) goto on_error;

    status = wss_connect(wss);
    if (status != PJ_SUCCESS) {
        wss->connected = PJ_FALSE;
        pjsip_transport_shutdown(&wss->base);
        return status;
    }

    status = pj_thread_create(pool, "wssworker", &wss_worker_thread, wss,
                               0, 0, &wss->thread);
    if (status != PJ_SUCCESS) goto on_error;

    if (p_tp) *p_tp = &wss->base;

    LOGI("WSS transport ready for %s:%d%s", server, port,
         (path && path[0]) ? path : "/");
    return PJ_SUCCESS;

on_error:
    if (wss->base.lock) { pj_lock_destroy(wss->base.lock); wss->base.lock = NULL; }
    if (wss->send_lock) { pj_lock_destroy(wss->send_lock); wss->send_lock = NULL; }
    if (wss->base.ref_cnt) { pj_atomic_destroy(wss->base.ref_cnt); wss->base.ref_cnt = NULL; }
    pjsip_endpt_release_pool(endpt, pool);
    return status;
}

PJ_DEF(pj_status_t) pjsip_wss_stop(pjsip_transport *tp)
{
    if (!tp) return PJ_EINVAL;
    return pjsip_transport_shutdown(tp);
}
