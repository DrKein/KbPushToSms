package kein.notitosms.ui

import android.content.Intent
import android.os.Build
import android.provider.Settings
import kein.notitosms.base.BaseMvpPresenterImp
import kein.notitosms.tools.PermissionChecker
import kein.notitosms.tools.PhoneNumberSaver

class MainPresenter(
        private val phoneNumberSaver: PhoneNumberSaver,
        private val permissionChecker: PermissionChecker
) : BaseMvpPresenterImp<IMainView>(), IMainPresenter {

    override fun checkPermission() {
        if (!permissionChecker.checkNotiPermission()) {
            view?.requestNotificationPermission()
        }
        if (!permissionChecker.checkSendSmsPermission()) {
            view?.requestSendSmsPermission()
        }
    }

    override fun getNotiSettingIntent(): Intent =
            Intent(if (Build.VERSION.SDK_INT >= 22) {
                Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
            } else {
                "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
            })

    override fun showSavedNumber() {
        val savedNumber = phoneNumberSaver.getPhoneNumber()
        view?.showSavedNumber(if (savedNumber.isEmpty()) {
            "input number"
        } else {
            savedNumber
        })
    }

    override fun savePhoneNumber(phoneNumber: String) {
        phoneNumberSaver.savePhoneNumber(phoneNumber)
        showSavedNumber()
    }
}