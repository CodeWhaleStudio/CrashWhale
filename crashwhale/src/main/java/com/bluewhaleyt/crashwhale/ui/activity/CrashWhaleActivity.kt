package com.bluewhaleyt.crashwhale.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bluewhaleyt.crashwhale.CrashWhaleExceptionKeys
import com.bluewhaleyt.crashwhale.ExceptionData
import com.bluewhaleyt.crashwhale.ui.screen.ExceptionScreen
import com.bluewhaleyt.crashwhale.ui.theme.CrashWhaleTheme

internal class CrashWhaleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = CrashWhaleExceptionKeys.Type.key.get(intent)
        val message = CrashWhaleExceptionKeys.Message.key.get(intent)
        val cause = CrashWhaleExceptionKeys.Cause.key.get(intent)
        val stacktrace = CrashWhaleExceptionKeys.StackTrace.key.get(intent)
        val time = CrashWhaleExceptionKeys.Time.key.get(intent)

        val manufacturer = CrashWhaleExceptionKeys.Manufacturer.key.get(intent)
        val device = CrashWhaleExceptionKeys.Device.key.get(intent)
        val androidOsVersion = CrashWhaleExceptionKeys.AndroidOSVersion.key.get(intent)
        val androidSdkVersion = CrashWhaleExceptionKeys.AndroidSdkVersion.key.get(intent)

        setContent {
            CrashWhaleTheme {
                ExceptionScreen(
                    exceptionData = ExceptionData(
                        type = type,
                        message = message,
                        cause = cause,
                        stacktrace = stacktrace,
                        time = time,
                        manufacturer = manufacturer,
                        device = device,
                        androidOsVersion = androidOsVersion,
                        androidSdkVersion = androidSdkVersion
                    )
                )
            }
        }

    }

}

internal fun String.get(intent: Intent): String {
    return intent.getStringExtra(this) ?: ""
}