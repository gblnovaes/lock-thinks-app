package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityReaderNfcBinding


class ReaderNfcActivity : BaseActivity() {
    private lateinit var binding: ActivityReaderNfcBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReaderNfcBinding.inflate(layoutInflater)

        setContentView(binding.root)


        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTimer.text = "" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                finish()
            }
        }.start()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent?.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            tag?.let {
                handleNfcTag(it)
            }
        }
    }

    private fun handleNfcTag(tag: Tag) {
        val tagId = tag.id.joinToString(":") { String.format("%02X", it) }
        Toast.makeText(this, "Tag NFC detectada! ID: $tagId", Toast.LENGTH_LONG).show()
        val resultIntent = Intent()
        resultIntent.putExtra("reader_tag_result", "$tagId")
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}