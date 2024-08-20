package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import br.com.gabrielnovaes.lockthinksapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val splashDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        window.navigationBarColor = resources.getColor(R.color.black, theme)

        Handler(Looper.getMainLooper()).postDelayed({
             startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashDuration)
    }
}