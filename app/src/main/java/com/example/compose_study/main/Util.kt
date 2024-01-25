package com.example.compose_study.main

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
fun log(tag: String?, vararg message: Any?) {
    val filename = String.format(
        " (%s:%s)",
        Throwable().stackTrace[1].fileName,
        Throwable().stackTrace[1].lineNumber
    )
    val methodName = String.format(" %s() ", Throwable().stackTrace[1].methodName)
    var logMessage = ""
    message.forEach {
        logMessage += "$it "
    }
    Log.d(tag, methodName + logMessage + filename)
}