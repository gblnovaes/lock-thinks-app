package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.gabrielnovaes.lockthinksapp.R
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         val fab: FloatingActionButton = findViewById(R.id.fab)

         fab.setOnClickListener {
            Toast.makeText(this, "Fab Button Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}