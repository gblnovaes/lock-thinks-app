package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.gabrielnovaes.lockthinksapp.R
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            Toast.makeText(
                this,
                "Fab Button Clicked!",
                Toast.LENGTH_SHORT
            ).show()

            showTagRegisterDialog()
        }

        binding.lockTagBtnOpen.setOnClickListener {
            Toast.makeText(this, "Button Open Clicked!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showTagRegisterDialog() {

        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.custom_dialog_register_tag, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()

        val inputField = dialogView.findViewById<EditText>(R.id.dialog_input)
        val cancelButton = dialogView.findViewById<Button>(R.id.button_cancel)
        val registerButton = dialogView.findViewById<Button>(R.id.button_register)

        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        registerButton.setOnClickListener {
            val userInput = inputField.text.toString()
            println("Registro: $userInput")
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}