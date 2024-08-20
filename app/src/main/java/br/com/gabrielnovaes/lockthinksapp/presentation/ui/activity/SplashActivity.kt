package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import br.com.gabrielnovaes.lockthinksapp.R

class SplashActivity : AppCompatActivity() {

    private val splashDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
             startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashDuration)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.navigationBarColor = resources.getColor(R.color.black, theme)
        }
    }
}