import com.example.buildsrc.Apps
import com.example.buildsrc.Libs

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        minSdk = Apps.MIN_SDK
        targetSdk = Apps.TARGET_SDK
    }
}

dependencies {

    implementation(Libs.hilt)
    kapt(Libs.hiltAndroidCompiler)
    kapt(Libs.hiltCompiler)

    implementation(Libs.coroutines)
}