package com.example.buildsrc

object Apps{
    const val COMPILE_SDK = 33
    const val MIN_SDK = 23
    const val TARGET_SDK = 33
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val APPLICATION_ID = "com.example.compose_study"
}

object Versions{
    const val gradle = "7.2.2"
    const val kotlin = "1.6.10"
    const val jetbrains = "1.7.20"
    
    const val coreKtx = "1.6.0"
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.4"
    const val lifecycle = "2.4.0-rc01"

    const val junit = "4.13.2"
    const val runner = "1.1.4"
    const val espresso_core = "3.5.0"

    const val okhttp = "4.9.2"

    const val retrofit = "2.9.0"

    const val hiltPlugin = "2.39.1"
    const val hilt = "2.38.1"
    const val hiltViewModel = "1.0.0"
    const val hiltCompiler = "1.0.0"

    const val glide = "4.12.0"

    const val timber = "4.7.1"

    const val coroutines = "1.3.9"

    const val splashscreen = "1.0.0"

    const val paging = "3.1.0"

    const val shimmer = "0.5.0"

    const val room = "2.4.0"

    const val compose_activity = "1.5.1"

    const val compose_lifecycle = "2.5.1"

}

object Libs{
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val jetbrains = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.jetbrains}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltPlugin}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifeCycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltViewModel = "androidx.hilt:hilt-navigation-fragment:${Versions.hiltViewModel}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val splashscreen = "androidx.core:core-splashscreen:${Versions.splashscreen}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okhttpVersion = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val compose_material3 = "androidx.compose.material3:material3"
    // or Material Design 2
    const val compose_material = "androidx.compose.material:material"
    // or skip Material Design and build directly on top of foundational components
    const val compose_foundation = "androidx.compose.foundation:foundation"
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    const val compose_ui = "androidx.compose.ui:ui"

    // Android Studio Preview support
    const val compose_ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"

    // UI Tests
    const val compose_ui_test = "androidx.compose.ui:ui-test-junit4"
    const val compose_ui_debug = "androidx.compose.ui:ui-test-manifest"

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    const val compose_icons_core = "androidx.compose.material:material-icons-core"
    // Optional - Add full set of material icons
    const val compose_icons_extended = "androidx.compose.material:material-icons-extended"
    // Optional - Add window size utils
    const val compose_window_size = "androidx.compose.material3:material3-window-size-class"

    // Optional - Integration with activities
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    // Optional - Integration with ViewModels
    const val compose_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose_lifecycle}"
    // Optional - Integration with LiveData
    const val compose_livedata = "androidx.compose.runtime:runtime-livedata"
}

object TestLibs{
    const val junit = "junit:junit:${Versions.junit}"
    const val runner = "androidx.test.ext:junit:${Versions.runner}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}