import com.example.buildsrc.Apps
import com.example.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
android {

    namespace = Apps.APPLICATION_ID
    compileSdk = Apps.COMPILE_SDK

    defaultConfig {
        applicationId = Apps.APPLICATION_ID
        minSdk = Apps.MIN_SDK
        targetSdk = Apps.TARGET_SDK
        versionCode = Apps.VERSION_CODE
        versionName = Apps.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Libs.coreKtx)
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.lifeCycleKtx)
    implementation(Libs.livedataKtx)
    implementation(Libs.viewModelKtx)

    implementation(Libs.hiltViewModel)
    implementation(Libs.hilt)
    kapt(Libs.hiltAndroidCompiler)
    kapt(Libs.hiltCompiler)

    implementation(Libs.splashscreen)

    implementation(Libs.glide)
    kapt(Libs.glideAnnotation)

    implementation(Libs.retrofit)

    implementation(Libs.shimmer)
}