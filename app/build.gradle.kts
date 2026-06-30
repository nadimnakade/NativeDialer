import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.crm.v2"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.crm.v2"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "2.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }

        val apiBaseUrl = project.findProperty("API_BASE_URL") ?: "https://1up.co.in/DialerAPI/api/"
        val cloudCallUrl = project.findProperty("CLOUD_CALL_URL") ?: "https://crm2.cloud-connect.in/ccpl_api/v1.4/api/info/click2call"
        val cloudTokenId = project.findProperty("CLOUD_TOKEN_ID") ?: "varsvZnOX5Z7Ibd2"
        val cloudExtensionNumber = project.findProperty("CLOUD_EXTENSION_NUMBER") ?: "701"
        val cloudExtensionPassword = project.findProperty("CLOUD_EXTENSION_PASSWORD") ?: "b147a109a38e4d3137e7442e54dd0fd0"

        buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
        buildConfigField("String", "CLOUD_CALL_URL", "\"$cloudCallUrl\"")
        buildConfigField("String", "CLOUD_TOKEN_ID", "\"$cloudTokenId\"")
        buildConfigField("String", "CLOUD_EXTENSION_NUMBER", "\"$cloudExtensionNumber\"")
        buildConfigField("String", "CLOUD_EXTENSION_PASSWORD", "\"$cloudExtensionPassword\"")

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86_64")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    ndkVersion = "27.1.12297006"

    // PJSIP: native libs loaded from jniLibs/ — run scripts/build-pjsip.sh first
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("src/main/jniLibs")
        }
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi"
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    // PJSIP Java SDK (placed in libs/ by scripts/build-pjsip.sh)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.splashscreen)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    debugImplementation(libs.androidx.ui.tooling)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.work)
    kapt(libs.hilt.work.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    kapt(libs.androidx.room.compiler)

    // Retrofit + OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.play.services)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Security - Encrypted Preferences
    implementation(libs.androidx.security.crypto)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    // Accompanist
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.drawablepainter)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Biometric
    implementation(libs.androidx.biometric)

    // Paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
}
