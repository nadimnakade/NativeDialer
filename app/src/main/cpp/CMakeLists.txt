cmake_minimum_required(VERSION 3.22.1)

project("pjsip_bridge")

# PJSIP library paths (built from source)
set(PJSIP_DIR ${CMAKE_SOURCE_DIR}/../../../external/pjsip)
set(PJSIP_LIB_DIR ${PJSIP_DIR}/android/${ANDROID_ABI}/lib)

# JNI bridge source
add_library(pjsip_bridge SHARED
    pjsip_bridge.cpp
)

# Find required Android libraries
find_library(log-lib log)
find_library(android-lib android)

# Include PJSIP headers
target_include_directories(pjsip_bridge PRIVATE
    ${PJSIP_DIR}/pjlib/include
    ${PJSIP_DIR}/pjlib-util/include
    ${PJSIP_DIR}/pjnath/include
    ${PJSIP_DIR}/pjmedia/include
    ${PJSIP_DIR}/pjsip/include
)

# Link against PJSIP libraries and Android libs
target_link_directories(pjsip_bridge PRIVATE ${PJSIP_LIB_DIR})

target_link_libraries(pjsip_bridge
    ${log-lib}
    ${android-lib}
    pjsua2
    pjsip-ua
    pjsip-simple
    pjsip
    pjmedia-codec
    pjmedia
    pjnath
    pjlib-util
    pjsip
    pjlib
)
