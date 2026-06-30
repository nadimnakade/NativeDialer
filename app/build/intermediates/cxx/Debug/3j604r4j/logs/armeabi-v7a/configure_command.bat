@echo off
"C:\\Users\\Nadeem\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\Git_Repos\\Dialer\\Frontend\\app\\src\\main\\cpp" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=26" ^
  "-DANDROID_PLATFORM=android-26" ^
  "-DANDROID_ABI=armeabi-v7a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=armeabi-v7a" ^
  "-DANDROID_NDK=C:\\Users\\Nadeem\\AppData\\Local\\Android\\Sdk\\ndk\\27.1.12297006" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Nadeem\\AppData\\Local\\Android\\Sdk\\ndk\\27.1.12297006" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Nadeem\\AppData\\Local\\Android\\Sdk\\ndk\\27.1.12297006\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Nadeem\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Git_Repos\\Dialer\\Frontend\\app\\build\\intermediates\\cxx\\Debug\\3j604r4j\\obj\\armeabi-v7a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Git_Repos\\Dialer\\Frontend\\app\\build\\intermediates\\cxx\\Debug\\3j604r4j\\obj\\armeabi-v7a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Git_Repos\\Dialer\\Frontend\\app\\.cxx\\Debug\\3j604r4j\\armeabi-v7a" ^
  -GNinja
