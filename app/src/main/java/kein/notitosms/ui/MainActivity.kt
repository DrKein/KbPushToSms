package kein.notitosms.ui

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import kein.notitosms.R
import kein.notitosms.base.BaseMvpActivity
import kein.notitosms.tools.LastSendSms
import kein.notitosms.tools.PermissionChecker
import kein.notitosms.tools.PhoneNumberSaver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<IMainView, IMainPresenter>(), IMainView {

    private val TAG = this::class.java.simpleName

    override fun createPresenter(): IMainPresenter {
        return MainPresenter(
                PhoneNumberSaver(this),
                PermissionChecker(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.checkPermission()
        presenter.showSavedNumber()

        btnPhoneNum.setOnClickListener { showInputNumberDialog() }
    }

    override fun requestNotificationPermission() {
        AlertDialog.Builder(this)
                .setMessage("We need Access Notification Permission.")
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    startActivity(presenter.getNotiSettingIntent())
                })
                .show()
    }

    override fun requestSendSmsPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.SEND_SMS), 0)
    }

    override fun showSavedNumber(number: String) {
        btnPhoneNum.text = number
    }

    private fun showInputNumberDialog() {
        val editText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_PHONE
            addTextChangedListener(PhoneNumberFormattingTextWatcher())
            setText(btnPhoneNum.text)
            gravity = Gravity.CENTER
        }

        AlertDialog.Builder(this)
                .setTitle("Input Phone Number")
                .setView(editText)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    presenter.savePhoneNumber(editText.text.toString())
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show()
    }

    override fun onResume() {
        super.onResume()
        LastSendSms(this).getLastSms().let {
            if (!it.isEmpty()) {
                tvLastSms.text = it
            }
        }
    }

}
