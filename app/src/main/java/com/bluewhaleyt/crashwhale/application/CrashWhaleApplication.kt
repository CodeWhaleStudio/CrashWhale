package com.bluewhaleyt.crashwhale.application

import android.app.Application
import com.google.android.material.color.DynamicColors

class CrashWhaleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        applyDynamicColorsForXmlActivities()
    }

    private fun applyDynamicColorsForXmlActivities() {
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}