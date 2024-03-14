package com.bluewhaleyt.crashwhale

import android.os.Build
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Parcelize
internal data class ExceptionData(
    val type: String,
    val message: String,
    val cause: String,
    val stacktrace: String,
    val time: String,
    val manufacturer: String,
    val device: String,
    val androidOsVersion: String,
    val androidSdkVersion: String,
) : Parcelable

internal fun Throwable.process(): ExceptionData {
    val type = type()
    val rootCauseOfException = rootCause()
    val cause = rootCauseOfException.type()
    val message = rootCauseOfException.message ?: "Unknown error"
    val stacktrace = this.stackTraceToString()
    val time = DateFormat
        .getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, Locale.getDefault())
        .format(Calendar.getInstance().time)

    return ExceptionData(
        type = type,
        message = message,
        cause = cause,
        stacktrace = stacktrace,
        time = time,
        manufacturer = Build.MANUFACTURER,
        device = Build.MODEL,
        androidOsVersion = Build.VERSION.RELEASE,
        androidSdkVersion = Build.VERSION.SDK_INT.toString(),
    )
}

internal tailrec fun Throwable.rootCause(): Throwable {
    return if (cause == null) {
        this
    } else {
        cause!!.rootCause()
    }
}

internal fun Throwable.type(): String {
    return this::class.java.simpleName
}