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
    org.jetbrains.kotlin.kapt3.base.Kapt.kapt(Libs.hiltAndroidCompiler)
    org.jetbrains.kotlin.kapt3.base.Kapt.kapt(Libs.hiltCompiler)

    api(Libs.roomRuntime)
    org.jetbrains.kotlin.kapt3.base.Kapt.kapt(Libs.roomCompiler)
    implementation(Libs.roomKtx)
}