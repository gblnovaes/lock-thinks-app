package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import br.com.gabrielnovaes.lockthinksapp.R
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityHomeBinding
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityReaderNfcBinding
import com.google.android.material.snackbar.Snackbar

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

        binding.lockTagBtnClose.setOnClickListener {
            showSuccessSnackbarWithOkButton()
        }

        binding.lockTagBtnOpen.setOnClickListener {
            val intent = Intent(this, ReaderNfcActivity::class.java)
            startActivity(intent)
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


    private fun showSuccessSnackbarWithOkButton() {
        Snackbar.make(binding.root, R.string.lock_thinks_label_snack_success, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.colorSuccess))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction(R.string.lock_thinks_label_ok) {
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun showFailedSnackbarWithOkButton() {
        Snackbar.make(binding.root, R.string.lock_thinks_label_snack_error, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.colorError))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction(R.string.lock_thinks_label_ok) {
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }
}