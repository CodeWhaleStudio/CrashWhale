package com.bluewhaleyt.crashwhale

import android.os.Message
import android.os.Messenger
import androidx.core.os.bundleOf

class CrashWhaleExceptionHandler(
    private val serviceMessenger: Messenger,
    private val defaultHandler: Thread.UncaughtExceptionHandler?
) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        e.printStackTrace()
        val exceptionData = e.process()
        val message = Message().apply {
            data = bundleOf(
                CrashWhaleExceptionKeys.Type.key to exceptionData.type,
                CrashWhaleExceptionKeys.Message.key to exceptionData.message,
                CrashWhaleExceptionKeys.Cause.key to exceptionData.cause,
                CrashWhaleExceptionKeys.StackTrace.key to exceptionData.stacktrace,
                CrashWhaleExceptionKeys.Time.key to exceptionData.time,

                CrashWhaleExceptionKeys.Manufacturer.key to exceptionData.manufacturer,
                CrashWhaleExceptionKeys.Device.key to exceptionData.device,
                CrashWhaleExceptionKeys.AndroidOSVersion.key to exceptionData.androidOsVersion,
                CrashWhaleExceptionKeys.AndroidSdkVersion.key to exceptionData.androidSdkVersion,
            )
        }
        serviceMessenger.send(message)
        defaultHandler?.uncaughtException(t, e)
    }
}