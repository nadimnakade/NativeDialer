#!/usr/bin/env bash
set -euo pipefail
PJSIP_VERSION="2.15.1"
PJSIP_DIR="external/pjproject-${PJSIP_VERSION}"
export ANDROID_NDK_ROOT="${ANDROID_NDK_ROOT:-/home/naddy/android-ndk-r27c}"
HOST_OS="linux"
JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/java-17-openjdk-amd64}"
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"
OUTPUT_SO="${PROJECT_ROOT}/app/src/main/jniLibs"
OUTPUT_JAR="${PROJECT_ROOT}/app/libs"
echo "=== PJSIP Android Build ==="
echo "NDK: ${ANDROID_NDK_ROOT}"
if ! command -v swig &>/dev/null; then sudo apt-get update -qq && sudo apt-get install -y -qq swig; fi
cd "${PROJECT_ROOT}"
if [ ! -d "${PJSIP_DIR}" ]; then mkdir -p external && wget -q "https://github.com/pjsip/pjproject/archive/refs/tags/${PJSIP_VERSION}.tar.gz" -O /tmp/pjsip.tar.gz && tar -xzf /tmp/pjsip.tar.gz -C external/; fi
cd "${PJSIP_DIR}"
cat > pjlib/include/pj/config_site.h << 'CFGEOF'
#define PJ_CONFIG_ANDROID 1
#include <pj/config_site_sample.h>
CFGEOF
TOOLCHAIN="${ANDROID_NDK_ROOT}/toolchains/llvm/prebuilt/${HOST_OS}-x86_64"
WRAP_DIR="${PROJECT_ROOT}/toolchain-wrappers"
mkdir -p "${WRAP_DIR}"
build_abi() {
    local abi=$1 api=$2 cc_prefix=$3 host_triple=$4
    echo "=== Building ${abi} ==="
    cat > "${WRAP_DIR}/${host_triple}-gcc" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/${cc_prefix}clang" "\$@"
WRAPEOF
    chmod +x "${WRAP_DIR}/${host_triple}-gcc"
    cat > "${WRAP_DIR}/${host_triple}-g++" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/${cc_prefix}clang++" "\$@"
WRAPEOF
    chmod +x "${WRAP_DIR}/${host_triple}-g++"
    cat > "${WRAP_DIR}/${host_triple}-ar" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/llvm-ar" "\$@"
WRAPEOF
    chmod +x "${WRAP_DIR}/${host_triple}-ar"
    cat > "${WRAP_DIR}/${host_triple}-ranlib" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/llvm-ranlib" "\$@"
WRAPEOF
    chmod +x "${WRAP_DIR}/${host_triple}-ranlib"
    export PATH="${WRAP_DIR}:${PATH}"
    export CC="${WRAP_DIR}/${host_triple}-gcc"
    export CXX="${WRAP_DIR}/${host_triple}-g++"
    export AR="${WRAP_DIR}/${host_triple}-ar"
    export RANLIB="${WRAP_DIR}/${host_triple}-ranlib"
    export CFLAGS="-O2 -fPIC -D__ANDROID_API__=${api} -Wno-unguarded-availability"
    export CXXFLAGS="-O2 -fPIC -D__ANDROID_API__=${api} -fexceptions -frtti -Wno-unguarded-availability"
    export LDFLAGS="-lc -lm"
    ./configure --host="${host_triple}" --enable-shared --disable-static --disable-silk --disable-opus --disable-video --disable-l16-codec --disable-gsm-codec --disable-speex-codec --disable-ilbc-codec --disable-sdl --disable-ffmpeg --disable-v4l2 --disable-openh264 --disable-resample --disable-libyuv --disable-libsrtp --disable-libwebrtc --disable-android-mediacodec --with-ssl=no 2>&1
    make dep 2>&1
    rm -f pjmedia/src/pjmedia-codec/and_aud_mediacodec.cpp
    make clean 2>&1
    make -j"$(nproc)" 2>&1
    mkdir -p "${OUTPUT_SO}/${abi}"
    find . -name "*.so" -exec cp -L {} "${OUTPUT_SO}/${abi}/" \;
    make clean 2>&1
}
build_abi "armeabi-v7a" 21 "armv7a-linux-androideabi21-" "arm-linux-androideabi"
build_abi "arm64-v8a"   21 "aarch64-linux-android21-"    "aarch64-linux-android"
build_abi "x86_64"      21 "x86_64-linux-android21-"     "x86_64-linux-android"
echo ""
echo "=== Java SWIG bindings ==="
cat > "${WRAP_DIR}/aarch64-linux-android-gcc" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/aarch64-linux-android21-clang" "\$@"
WRAPEOF
chmod +x "${WRAP_DIR}/aarch64-linux-android-gcc"
cat > "${WRAP_DIR}/aarch64-linux-android-g++" << WRAPEOF
#!/bin/bash
exec "${TOOLCHAIN}/bin/aarch64-linux-android21-clang++" "\$@"
WRAPEOF
chmod +x "${WRAP_DIR}/aarch64-linux-android-g++"
export PATH="${WRAP_DIR}:${PATH}"
export CC="${WRAP_DIR}/aarch64-linux-android-gcc"
export CXX="${WRAP_DIR}/aarch64-linux-android-g++"
export AR="${WRAP_DIR}/aarch64-linux-android-ar"
export RANLIB="${WRAP_DIR}/aarch64-linux-android-ranlib"
export CFLAGS="-O2 -fPIC -D__ANDROID_API__=21 -Wno-unguarded-availability"
export CXXFLAGS="-O2 -fPIC -D__ANDROID_API__=21 -fexceptions -frtti -Wno-unguarded-availability"
export LDFLAGS="-lc -lm"
export JAVA_HOME
./configure --host="aarch64-linux-android" --enable-java --enable-shared --disable-static --disable-silk --disable-opus --disable-video --disable-l16-codec --disable-gsm-codec --disable-speex-codec --disable-ilbc-codec --disable-sdl --disable-ffmpeg --disable-v4l2 --disable-openh264 --disable-resample --disable-libyuv --disable-libsrtp --disable-libwebrtc --disable-android-mediacodec --with-ssl=no 2>&1
make dep 2>&1
rm -f pjmedia/src/pjmedia-codec/and_aud_mediacodec.cpp
make clean 2>&1
make -j"$(nproc)" swig 2>&1
mkdir -p "${OUTPUT_JAR}"
find . -name "pjsua2.jar" -exec cp {} "${OUTPUT_JAR}/" \;
echo ""
echo "=== Done ==="
find "${OUTPUT_SO}" -name "*.so" -exec ls -la {} \; 2>/dev/null || echo "No .so files"
ls -la "${OUTPUT_JAR}/pjsua2.jar" 2>/dev/null || echo "No JAR"
