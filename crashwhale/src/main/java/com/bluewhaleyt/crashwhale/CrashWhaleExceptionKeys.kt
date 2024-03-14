package com.bluewhaleyt.crashwhale

import android.content.Intent

private val prefix = "com.bluewhaleyt.crashwhale.exception"

internal enum class CrashWhaleExceptionKeys(
    val key: String
) {
    Type("$prefix.type"),
    Message("$prefix.message"),
    Cause("$prefix.cause"),
    StackTrace("$prefix.stacktrace"),
    Time("$prefix.time"),

    Manufacturer("$prefix.manufacturer"),
    Device("$prefix.device"),
    AndroidOSVersion("$prefix.android.os.version"),
    AndroidSdkVersion("$prefix.android.sdk.version")
}