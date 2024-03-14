package com.bluewhaleyt.crashwhale

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.bluewhaleyt.crashwhale.ui.activity.CrashWhaleActivity

internal class CrashWhaleHandler(
    private val context: Context
) : Handler(Looper.getMainLooper()) {

    override fun handleMessage(message: Message) {
        val intent = Intent(context, CrashWhaleActivity::class.java)
        intent.apply {
            putExtras(message.data)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

}