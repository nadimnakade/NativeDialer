# PJSIP Android Build Script
# Run this from the project root or CI pipeline

set -e

PJSIP_VERSION="2.15.1"
PJSIP_DIR="external/pjsip"
NDK_VERSION="27.1.12297006"
ANDROID_NDK_ROOT="${ANDROID_HOME}/ndk/${NDK_VERSION}"

echo "=== PJSIP Android Build ==="
echo "NDK: ${ANDROID_NDK_ROOT}"

# Step 1: Download PJSIP if not present
if [ ! -d "${PJSIP_DIR}" ]; then
    echo "Downloading PJSIP ${PJSIP_VERSION}..."
    mkdir -p external
    wget "https://github.com/pjsip/pjsip/archive/refs/tags/${PJSIP_VERSION}.tar.gz" -O /tmp/pjsip.tar.gz
    tar -xzf /tmp/pjsip.tar.gz -C external/
    mv "external/pjsip-${PJSIP_VERSION}" "${PJSIP_DIR}"
fi

# Step 2: Configure for Android
cd "${PJSIP_DIR}"
cat > pjlib/include/pj/config_site.h << 'EOF'
#define PJ_CONFIG_ANDROID 1
#include <pj/config_site_sample.h>
EOF

# Step 3: Build for all ABIs
for ABI in armeabi-v7a arm64-v8a x86_64; do
    echo "Building for ${ABI}..."
    
    case ${ABI} in
        armeabi-v7a) TARGET="armv7a-linux-androideabi" ;;
        arm64-v8a)   TARGET="aarch64-linux-android" ;;
        x86_64)      TARGET="x86_64-linux-android" ;;
    esac
    
    export ANDROID_NDK_ROOT="${ANDROID_NDK_ROOT}"
    ./configure-android --android --with-ssl --use-ndk \
        --host="${TARGET}" \
        --enable-shared \
        --disable-static \
        CFLAGS="-O2" \
        LDFLAGS=""
    
    make dep
    make clean
    make -j$(nproc)
    
    # Copy built libraries to app external directory
    mkdir -p "../../app/src/main/jniLibs/${ABI}"
    find . -name "*.so" -path "*/android/*" -exec cp {} "../../app/src/main/jniLibs/${ABI}/" \;
    
    make clean
done

echo "=== PJSIP Build Complete ==="
echo "Libraries at: app/src/main/jniLibs/"
