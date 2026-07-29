#ifndef PJSIP_WSS_TRANSPORT_H__
#define PJSIP_WSS_TRANSPORT_H__

#include <pjsip/sip_transport.h>

PJ_BEGIN_DECL

typedef struct pjsip_wss_transport pjsip_wss_transport;

PJ_DECL(pj_status_t) pjsip_wss_create(pjsip_endpoint *endpt,
                                       const char *server,
                                       int port,
                                       const char *path,
                                       pjsip_transport **p_tp);

PJ_DECL(pj_status_t) pjsip_wss_stop(pjsip_transport *tp);

PJ_END_DECL

#endif
