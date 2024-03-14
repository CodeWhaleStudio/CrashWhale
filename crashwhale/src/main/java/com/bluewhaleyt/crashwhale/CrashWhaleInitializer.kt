package com.bluewhaleyt.crashwhale

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Messenger
import androidx.startup.Initializer

internal class CrashWhaleInitializer : Initializer<CrashWhaleInitializer.InitializedToken> {

    object InitializedToken

    override fun create(context: Context): InitializedToken {
        val connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val messenger = Messenger(service)
                val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
                val exceptionHandler = CrashWhaleExceptionHandler(
                    serviceMessenger = messenger,
                    defaultHandler = defaultExceptionHandler
                )
                Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
            }
            override fun onServiceDisconnected(name: ComponentName?) {

            }
        }
        val intent = Intent(context, CrashWhaleService::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)

        return InitializedToken
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}