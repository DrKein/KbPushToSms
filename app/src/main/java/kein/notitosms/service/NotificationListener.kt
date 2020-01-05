package kein.notitosms.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.telephony.SmsManager
import android.widget.Toast
import kein.notitosms.tools.LastSendSms
import kein.notitosms.tools.PhoneNumberSaver
import kein.notitosms.tools.TargetAppList


class NotificationListener : NotificationListenerService() {

    private val targetAppList = TargetAppList()

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val extras = sbn?.takeIf {
            targetAppList.isTargetApp(sbn.packageName)
        }?.notification?.extras

        val message = extras?.getString("android.text").orEmpty()
        val phone = PhoneNumberSaver(this).getPhoneNumber()

        if (message.isNotEmpty() && phone.isNotEmpty()) {
            sendSms(phone, message)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendSms(phone: String, message: String) {
        SmsManager.getDefault().sendTextMessage(phone, null, message, null, null)
        LastSendSms(this).setLastSms(message)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        // Do nothing
    }

}