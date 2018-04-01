package kein.notitosms.tools

import android.content.Context
import android.content.SharedPreferences

class PhoneNumberSaver(ctx: Context) {

    private val TAG = this::class.java.simpleName

    private val pref: SharedPreferences
    private val KEY_NUMBER = "key_number"

    init {
        this.pref = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE)
    }

    fun savePhoneNumber(number: String) {
        pref.edit().putString(KEY_NUMBER, number).apply()
    }

    fun getPhoneNumber(): String {
        return pref.getString(KEY_NUMBER, "")
    }
}