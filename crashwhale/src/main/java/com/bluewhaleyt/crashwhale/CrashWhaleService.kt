package com.bluewhaleyt.crashwhale

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Messenger

internal class CrashWhaleService : Service() {

    private val handler by lazy {
        CrashWhaleHandler(applicationContext)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return Messenger(handler).binder
    }

}