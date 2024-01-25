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
        kotlinCompilerExtensionVersion = "1.4.8"
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

    //implementation(Libs.shimmer)

    val composeBom = platform("androidx.compose:compose-bom:2022.12.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)


    implementation(Libs.compose_material3)
    // or Material Design 2
    implementation(Libs.compose_material)
    // or skip Material Design and build directly on top of foundational components
    implementation(Libs.compose_foundation)
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation(Libs.compose_ui)


    // Android Studio Preview support
    implementation(Libs.compose_ui_tooling_preview)
    debugImplementation(Libs.compose_ui_tooling)

    // UI Tests
    androidTestImplementation(Libs.compose_ui_test)
    debugImplementation(Libs.compose_ui_debug)



    implementation(Libs.compose_icons_core)
    implementation(Libs.compose_util)
    // Optional - Add full set of material icons
    implementation(Libs.compose_icons_extended)
    // Optional - Add window size utils
    implementation(Libs.compose_window_size)

    // Optional - Integration with activities
    implementation(Libs.compose_activity)
    // Optional - Integration with ViewModels
    implementation(Libs.compose_viewmodel)
    // Optional - Integration with LiveData
    implementation(Libs.compose_livedata)

    implementation(Libs.onebone)

    implementation(Libs.coil)

    implementation(Libs.photo_view)
}