package kein.notitosms.tools

import android.content.Context
import android.content.SharedPreferences

class LastSendSms(ctx: Context) {

    private val TAG = this::class.java.simpleName
    private val pref: SharedPreferences
    private val KEY_LAST_SMS = "key_last_sms"

    init {
        pref = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }

    fun getLastSms() = pref.getString(KEY_LAST_SMS, "").orEmpty()

    fun setLastSms(sms: String) {
        pref.edit().putString(KEY_LAST_SMS, sms).apply()
    }

}