#!/usr/bin/env bash
# PJSIP Android Build Script — produces libpjsua2.so + pjsua2.jar for all ABIs
# Usage:
#   Option A: export ANDROID_NDK_ROOT=/path/to/android-ndk-r27c
#   Option B: export ANDROID_HOME=/path/to/sdk  (finds ndk inside it)
#   Then: bash scripts/build-pjsip.sh
#
set -euo pipefail

PJSIP_VERSION="2.15.1"
PJSIP_TARBALL="v${PJSIP_VERSION}.tar.gz"
PJSIP_URL="https://github.com/pjsip/pjproject/archive/refs/tags/${PJSIP_TARBALL}"
PJSIP_DIR="external/pjproject-${PJSIP_VERSION}"

# Detect NDK root
if [ -z "${ANDROID_NDK_ROOT:-}" ]; then
    if [ -n "${ANDROID_HOME:-}" ]; then
        # Find the latest NDK version
        NDK_VERSION=$(ls "${ANDROID_HOME}/ndk/" 2>/dev/null | sort -V | tail -1)
        if [ -n "$NDK_VERSION" ]; then
            ANDROID_NDK_ROOT="${ANDROID_HOME}/ndk/${NDK_VERSION}"
            echo "Detected NDK: ${ANDROID_NDK_ROOT}"
        else
            echo "ERROR: No NDK found in ${ANDROID_HOME}/ndk/"
            echo "Set ANDROID_NDK_ROOT or ANDROID_HOME"
            exit 1
        fi
    else
        echo "ERROR: Set ANDROID_NDK_ROOT or ANDROID_HOME"
        echo "Example: export ANDROID_NDK_ROOT=/opt/android-ndk-r27c"
        exit 1
    fi
fi

HOST_OS="linux"
SWIG_BIN="swig"
JAVA_HOME="${JAVA_HOME:-/usr/lib/jvm/java-17-openjdk-amd64}"
OUTPUT_DIR="app/src/main/jniLibs"
PROJECT_ROOT="$(cd "$(dirname "$0")/.." && pwd)"

echo "=== PJSIP Android Build ==="
echo "NDK: ${ANDROID_NDK_ROOT}"
echo "Java: ${JAVA_HOME}"
echo "Project: ${PROJECT_ROOT}"

# Step 1: Install dependencies if missing
if ! command -v swig &>/dev/null; then
    echo "Installing SWIG..."
    sudo apt-get update -qq && sudo apt-get install -y -qq swig openjdk-17-jdk wget tar make
fi

# Step 2: Download PJSIP
cd "${PROJECT_ROOT}"
if [ ! -d "${PJSIP_DIR}" ]; then
    echo "Downloading PJSIP ${PJSIP_VERSION}..."
    mkdir -p external
    wget -q "${PJSIP_URL}" -O "/tmp/${PJSIP_TARBALL}"
    tar -xzf "/tmp/${PJSIP_TARBALL}" -C external/
fi

cd "${PJSIP_DIR}"

# Step 3: Configure site header for Android
cat > pjlib/include/pj/config_site.h << 'CFGEOF'
#define PJ_CONFIG_ANDROID 1
#include <pj/config_site_sample.h>
CFGEOF

# Step 4: Build for each ABI
ABIS=(
    "armeabi-v7a:armv7a-linux-androideabi:16"
    "arm64-v8a:aarch64-linux-android:21"
    "x86_64:x86_64-linux-android:21"
)

for entry in "${ABIS[@]}"; do
    ABI="${entry%%:*}"
    TARGET="${entry#*:}"
    TARGET="${TARGET%:*}"
    API="${entry##*:}"
    TOOLCHAIN="${ANDROID_NDK_ROOT}/toolchains/llvm/prebuilt/${HOST_OS}-x86_64"

    echo ""
    echo "=== Building for ${ABI} ==="

    export CC="${TOOLCHAIN}/bin/${TARGET}${API}-clang"
    export CXX="${TOOLCHAIN}/bin/${TARGET}${API}-clang++"
    export AR="${TOOLCHAIN}/bin/llvm-ar"
    export RANLIB="${TOOLCHAIN}/bin/llvm-ranlib"
    export LD="${TOOLCHAIN}/bin/ld"
    export ANDROID_NDK_ROOT

    CFLAGS="-O2 -fPIC -D__ANDROID_API__=${API}" \
    LDFLAGS="" \
    ./configure-android \
        --host="${TARGET}" \
        --with-java="${JAVA_HOME}" \
        --with-swig="${SWIG_BIN}" \
        --enable-shared \
        --disable-static \
        --disable-silk \
        --disable-opus \
        --disable-video \
        --disable-l16-codec \
        --disable-gsm-codec \
        --disable-speex-codec \
        --disable-ilbc-codec \
        --disable-sdl \
        --disable-ffmpeg \
        --disable-v4l2 \
        --disable-openh264 \
        --disable-resample

    make dep
    make clean
    make -j"$(nproc)"

    # Copy .so files
    mkdir -p "${PROJECT_ROOT}/${OUTPUT_DIR}/${ABI}"
    find . -name "*.so" -exec cp -L {} "${PROJECT_ROOT}/${OUTPUT_DIR}/${ABI}/" \;

    make clean
done

# Step 5: Copy Java SDK JAR
echo ""
echo "=== Copying pjsua2 JAR ==="
mkdir -p "${PROJECT_ROOT}/app/libs"
find . -name "pjsua2.jar" -exec cp {} "${PROJECT_ROOT}/app/libs/" \;

echo ""
echo "=== Build Complete ==="
echo "Native libs: ${PROJECT_ROOT}/${OUTPUT_DIR}/"
ls -la "${PROJECT_ROOT}/${OUTPUT_DIR}"/*/ 2>/dev/null || true
echo ""
echo "Java SDK:    ${PROJECT_ROOT}/app/libs/pjsua2.jar"
ls -la "${PROJECT_ROOT}/app/libs/" 2>/dev/null || true
