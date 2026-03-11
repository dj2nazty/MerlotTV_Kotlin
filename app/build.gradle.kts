plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace  = "com.merlottv.app"
    compileSdk = 35

    defaultConfig {
        applicationId  = "com.merlottv.app"
        minSdk         = 21          // Android 5 — covers all modern Android TV hardware
        targetSdk      = 35
        versionCode    = 5
        versionName    = "2.3.0"

        // Room schema export
        kapt {
            arguments { arg("room.schemaLocation", "$projectDir/schemas") }
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable        = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    // TV apps are always landscape
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // TV / Leanback
    implementation(libs.androidx.leanback)
    implementation(libs.androidx.leanback.preference)

    // Layout
    implementation(libs.androidx.constraintlayout)

    // Media3 / ExoPlayer
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.hls)
    implementation(libs.media3.exoplayer.rtsp)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.datasource.okhttp)
    implementation(libs.media3.ui)
    implementation(libs.media3.session)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // DI — Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.livedata.ktx)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // DataStore
    implementation(libs.datastore.preferences)

    // Image loading
    implementation(libs.coil)

    // Navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // WorkManager
    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.work)
    kapt(libs.hilt.work.compiler)

    // JSON
    implementation(libs.gson)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
}

// Allow references to generated code
kapt { correctErrorTypes = true }
