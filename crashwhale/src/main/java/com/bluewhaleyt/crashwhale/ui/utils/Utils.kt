package com.bluewhaleyt.crashwhale.ui.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager





internal fun String.copyToClipboard(
    context: Context,
    label: String? = null
) {
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, this)
    clipboard.setPrimaryClip(clip)
}

internal fun String.share(
    context: Context,
    title: String? = "Share"
) {
    val sendIntent = Intent().apply {
        this.action = Intent.ACTION_SEND
        this.putExtra(Intent.EXTRA_TEXT, this@share)
        this.type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, title)
    context.startActivity(shareIntent)
}

internal fun Context.restartApplication() {
    val packageManager = applicationContext.packageManager
    val packageName = applicationContext.packageName

    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        launchIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(launchIntent)
    }
}

val Context.applicationVersionName: String
    get() {
        var version = ""
        try {
            val pInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            version = pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return version
    }