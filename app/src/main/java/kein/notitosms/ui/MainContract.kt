package kein.notitosms.ui

import android.content.Intent
import kein.notitosms.base.BaseMvpPresenter
import kein.notitosms.base.BaseMvpView

interface IMainView: BaseMvpView {
    fun requestNotificationPermission()
    fun showSavedNumber(number: String)
    fun requestSendSmsPermission()
}

interface IMainPresenter: BaseMvpPresenter<IMainView> {

    fun checkPermission()
    fun getNotiSettingIntent(): Intent
    fun showSavedNumber()
    fun savePhoneNumber(phoneNumber: String)

}