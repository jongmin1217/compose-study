buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.example.buildsrc.Libs.gradle)
        classpath(com.example.buildsrc.Libs.kotlin)
        classpath(com.example.buildsrc.Libs.hiltPlugin)


    }
}
plugins {
    id("com.android.application") version "7.1.3" apply false
    id("com.android.library") version "7.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}