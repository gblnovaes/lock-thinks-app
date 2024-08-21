package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityReaderNfcBinding


class ReaderNfcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReaderNfcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReaderNfcBinding.inflate(layoutInflater)

        setContentView(binding.root)


        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTimer.text =  "" + millisUntilFinished / 1000
            }

            override fun onFinish() {
                finish()
            }
        }.start()
    }
}