cmake_minimum_required(VERSION 3.22.1)
project(pjsip_bridge C)

set(PJSIP_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/../../../../../../Dialer - Copy/pjsip")
set(MBEDTLS_ROOT "${CMAKE_CURRENT_SOURCE_DIR}/../../../../../../Dialer - Copy/mbedtls")

if(ANDROID_ABI STREQUAL "arm64-v8a")
    set(PJSIP_BUILD "${PJSIP_ROOT}/build_android")
    set(MBEDTLS_BUILD "${MBEDTLS_ROOT}/build_android")
elseif(ANDROID_ABI STREQUAL "armeabi-v7a")
    set(PJSIP_BUILD "${PJSIP_ROOT}/build_android_v7a")
    set(MBEDTLS_BUILD "${MBEDTLS_ROOT}/build_android_v7a")
else()
    message(FATAL_ERROR "Unsupported ABI: ${ANDROID_ABI}")
endif()

add_library(pjsip_bridge SHARED
    pjsip_jni.c
    pjsip_wss_transport.c
)

target_include_directories(pjsip_bridge PRIVATE
    "${PJSIP_ROOT}/pjlib/include"
    "${PJSIP_ROOT}/pjlib-util/include"
    "${PJSIP_ROOT}/pjnath/include"
    "${PJSIP_ROOT}/pjmedia/include"
    "${PJSIP_ROOT}/pjsip/include"
    "${PJSIP_BUILD}/pjlib/include"
    "${PJSIP_BUILD}/pjsip/include"
    "${PJSIP_BUILD}/pjmedia/include"
    "${MBEDTLS_ROOT}/include"
    "${MBEDTLS_BUILD}/include"
)

target_compile_definitions(pjsip_bridge PRIVATE
    PJ_AUTOCONF=1
    PJ_IS_LITTLE_ENDIAN=1
    PJ_IS_BIG_ENDIAN=0
    PJ_HAS_IFADDRS_H=1
    PJ_JNI_HAS_JNI_ONLOAD=0
    PJ_HAS_SSL_SOCK=1
    PJ_SSL_SOCK_IMP=PJ_SSL_SOCK_IMP_MBEDTLS
    PJ_HAS_FLOATING_POINT=1
)

target_link_libraries(pjsip_bridge
    "${PJSIP_BUILD}/pjsip/libpjsua-lib.a"
    "${PJSIP_BUILD}/pjsip/libpjsip-ua.a"
    "${PJSIP_BUILD}/pjsip/libpjsip-simple.a"
    "${PJSIP_BUILD}/pjsip/libpjsip.a"
    "${PJSIP_BUILD}/pjmedia/libpjmedia-audiodev.a"
    "${PJSIP_BUILD}/pjmedia/libpjmedia-codec.a"
    "${PJSIP_BUILD}/pjmedia/libpjmedia-videodev.a"
    "${PJSIP_BUILD}/pjmedia/libpjmedia.a"
    "${PJSIP_BUILD}/pjnath/libpjnath.a"
    "${PJSIP_BUILD}/pjlib-util/libpjlib-util.a"
    "${PJSIP_BUILD}/pjlib/libpjlib.a"
    "${PJSIP_BUILD}/third_party/srtp/libsrtp.a"
    "${PJSIP_BUILD}/third_party/speex/libspeex.a"
    "${PJSIP_BUILD}/third_party/webrtc/libwebrtc.a"
    "${PJSIP_BUILD}/third_party/webrtc_aec3/libwebrtc_aec3.a"
    "${PJSIP_BUILD}/third_party/gsm/libgsm.a"
    "${PJSIP_BUILD}/third_party/ilbc/libilbc.a"
    "${PJSIP_BUILD}/third_party/g7221/libg7221.a"
    "${PJSIP_BUILD}/third_party/resample/libresample.a"
    "${PJSIP_BUILD}/third_party/yuv/libyuv.a"
    "${MBEDTLS_BUILD}/library/libmbedtls.a"
    "${MBEDTLS_BUILD}/library/libmbedx509.a"
    "${MBEDTLS_BUILD}/library/libmbedcrypto.a"
    android
    log
    OpenSLES
    atomic
)
