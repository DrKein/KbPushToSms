package kein.notitosms.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.telephony.SmsManager
import android.widget.Toast
import kein.notitosms.tools.LastSendSms
import kein.notitosms.tools.PhoneNumberSaver


class NotificationListener : NotificationListenerService() {

    private val TAG = javaClass.simpleName

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val extras = sbn?.takeIf {
            sbn.packageName == "com.kbstar.starpush"
        }?.notification?.extras

        val message = extras?.getString("android.text").orEmpty()
        val phone = PhoneNumberSaver(this).getPhoneNumber()
        if (!message.isEmpty() && !phone.isEmpty()) {
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