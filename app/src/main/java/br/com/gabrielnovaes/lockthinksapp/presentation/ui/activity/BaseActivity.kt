package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gabrielnovaes.lockthinksapp.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    var nfcAdapter: NfcAdapter? = null

    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, R.string.lock_thinks_nfc_not_found, Toast.LENGTH_LONG).show()
            return
        }

        if (!nfcAdapter!!.isEnabled) {
            showNfcSettingsSnackbar()
        }

    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }
        val intentFilter = arrayOf<IntentFilter>()

        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, intentFilter, null)
    }


    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    private fun showNfcSettingsSnackbar() {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            R.string.lock_thinks_nfc_label_activate,
            Snackbar.LENGTH_INDEFINITE
        )

        snackbar.setAction(R.string.lock_thinks_nfc_active_setting) {
            val intent = Intent(Settings.ACTION_NFC_SETTINGS)
            startActivity(intent)
        }

        snackbar.show()
    }

}