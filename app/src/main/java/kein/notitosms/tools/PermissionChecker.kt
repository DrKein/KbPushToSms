package kein.notitosms.tools

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.support.v4.content.ContextCompat
import kein.notitosms.service.NotificationListener

class PermissionChecker(private val ctx: Context) {

    /** check we have notification listener permission */
    fun checkNotiPermission(): Boolean {
        val flat: String? = Settings.Secure.getString(ctx.contentResolver, "enabled_notification_listeners")
        val cn = ComponentName(ctx, NotificationListener::class.java)
        return flat?.contains(cn.flattenToString()) == true
    }

    /** check we has send sms permission */
    fun checkSendSmsPermission() : Boolean {
        return ContextCompat.checkSelfPermission(ctx, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED
    }

}