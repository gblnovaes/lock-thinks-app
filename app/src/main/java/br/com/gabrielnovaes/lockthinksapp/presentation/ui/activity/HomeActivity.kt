package br.com.gabrielnovaes.lockthinksapp.presentation.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import br.com.gabrielnovaes.lockthinksapp.R
import br.com.gabrielnovaes.lockthinksapp.databinding.ActivityHomeBinding
import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.presentation.viewmodel.NfcTagRegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val nfcTagRegisterViewModel: NfcTagRegisterViewModel by viewModels()

    private lateinit var getResultLauncher: ActivityResultLauncher<Intent>

    private val hasOneRegisterTag = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            showTagRegisterDialog()
        }

        nfcTagRegisterViewModel.getStatus()

        binding.lockTagBtnClose.setOnClickListener {
            nfcTagRegisterViewModel.getStatusChanged()
        }

        binding.lockTagBtnOpen.setOnClickListener {
            val intent = Intent(this, ReaderNfcActivity::class.java)
            getResultLauncher.launch(intent)
        }

        if (nfcAdapter == null) {
            binding.fab.visibility = View.GONE
            binding.nfcNotAvailable.visibility = View.VISIBLE
            nfcTagRegisterViewModel.setClosedLockVisibility(false)
            nfcTagRegisterViewModel.setTagNotRegistered(false)
            nfcTagRegisterViewModel.setOpenLockVisibility(false)
            return
        }

        setupObservers()


        getResultLauncher()

        nfcTagRegisterViewModel.loadTexts()
    }


    private fun getResultLauncher() {
        getResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val returnedResult = data?.getStringExtra("reader_tag_result")

                val tag = nfcTagRegisterViewModel.tag.value.map {
                    NfcTag(
                        it.id,
                        it.tag
                    )
                }

                if (returnedResult == tag.first().tag) {
                    lifecycleScope.launch {
                        showOpenLock()
                        nfcTagRegisterViewModel.setStatus(false)
                    }

                } else {
                    playErrorSound()
                    showFailedSnackbarWithOkButton()
                }
            } else {
                Toast.makeText(
                    this,
                    R.string.lock_thinks_nfc_not_approached,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {

            nfcTagRegisterViewModel.isClosed.collect { status ->

                if (status) {
                    showCloseLock()
                    nfcTagRegisterViewModel.setStatus(true)
                } else {
                    showOpenLock()
                    nfcTagRegisterViewModel.setStatus(false)
                }
            }

        }

        lifecycleScope.launch {
            nfcTagRegisterViewModel.isOpenLock.collect { isVisible ->
                binding.lockTagOpen.visibility =
                    if (isVisible && !nfcTagRegisterViewModel.isClosed.value) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }

        lifecycleScope.launch {
            nfcTagRegisterViewModel.isClosedLock.collect { isVisible ->
                binding.lockTagClose.visibility =
                    if (isVisible && nfcTagRegisterViewModel.isClosed.value) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

        }

        lifecycleScope.launch {
            nfcTagRegisterViewModel.isNotTagRegistered.collect { isVisible ->
                binding.tagNotFound.visibility = if (isVisible) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }

        lifecycleScope.launch {

            nfcTagRegisterViewModel.getSize()

            val currentSize = nfcTagRegisterViewModel.tagListSize.value

            if (currentSize == hasOneRegisterTag) {
                binding.fab.visibility = View.GONE
            } else {
                showNotFoundTag()
            }
        }
    }

    private fun showNotFoundTag() {
        nfcTagRegisterViewModel.tagNotRegistered()
        nfcTagRegisterViewModel.setClosedLockVisibility(false)
        nfcTagRegisterViewModel.setOpenLockVisibility(false)
        nfcTagRegisterViewModel.setTagNotRegistered(true)
    }

    private fun showOpenLock() {

        nfcTagRegisterViewModel.openLock()
        nfcTagRegisterViewModel.setTagNotRegistered(false)
        nfcTagRegisterViewModel.setClosedLockVisibility(false)
        nfcTagRegisterViewModel.setOpenLockVisibility(true)

    }

    private fun showCloseLock() {
        nfcTagRegisterViewModel.closedLock()
        nfcTagRegisterViewModel.setTagNotRegistered(false)
        nfcTagRegisterViewModel.setOpenLockVisibility(false)
        nfcTagRegisterViewModel.setClosedLockVisibility(true)
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
            nfcTagRegisterViewModel.addText(userInput)
            showSuccessSnackbarWithOkButton()
            showOpenLock()
            binding.fab.visibility = View.GONE
            alertDialog.dismiss()
        }

        alertDialog.show()
    }


    private fun showSuccessSnackbarWithOkButton() {
        Snackbar.make(
            binding.root,
            R.string.lock_thinks_label_snack_success,
            Snackbar.LENGTH_LONG
        )
            .setBackgroundTint(ContextCompat.getColor(this, R.color.colorSuccess))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction(R.string.lock_thinks_label_ok) {
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun showFailedSnackbarWithOkButton() {
        Snackbar.make(
            binding.root,
            R.string.lock_thinks_label_snack_error,
            Snackbar.LENGTH_LONG
        )
            .setBackgroundTint(ContextCompat.getColor(this, R.color.colorError))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction(R.string.lock_thinks_label_ok) {
            }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }
}