package br.com.gabrielnovaes.lockthinksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.AddNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetPreferencesNfcStatusUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.SaveNfcPreferencesStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NfcTagRegisterViewModel @Inject constructor(
    private val addNfcTagUseCase: AddNfcTagUseCase,
    private val getNfcTagUseCase: GetNfcTagUseCase,
    private val getPreferencesNfcStatusUseCase: GetPreferencesNfcStatusUseCase,
    private val saveNfcPreferencesStatusUseCase: SaveNfcPreferencesStatusUseCase
) : ViewModel() {

    private val _tagText = MutableStateFlow<List<NfcTag>>(emptyList())
    val tag: StateFlow<List<NfcTag>> = _tagText

    private val _tagListSize = MutableStateFlow(0)
    val tagListSize: StateFlow<Int> = _tagListSize


    private val _isOpenLock = MutableStateFlow(false)
    val isOpenLock: StateFlow<Boolean> = _isOpenLock

    private val _isClosedLock = MutableStateFlow(false)
    val isClosedLock: StateFlow<Boolean> = _isClosedLock


    private val _isNotTagRegistered = MutableStateFlow(false)
    val isNotTagRegistered: StateFlow<Boolean> = _isNotTagRegistered

    private val _isClosed = MutableStateFlow(false)
    val isClosed: StateFlow<Boolean> = _isClosed


    fun addText(text: String) {
        viewModelScope.launch {
            addNfcTagUseCase(text)
            loadTexts()
        }
    }


    fun loadTexts() {
        viewModelScope.launch {
            _tagText.value = getNfcTagUseCase()
        }
    }


    fun openLock() {
        _isOpenLock.update { !it }
    }

    fun setOpenLockVisibility(isVisible: Boolean) {
        _isOpenLock.value = isVisible
    }

    fun closedLock() {
        _isClosedLock.update { !it }
    }

    fun setClosedLockVisibility(isVisible: Boolean) {
        _isClosedLock.value = isVisible
    }

    fun tagNotRegistered() {
        _isNotTagRegistered.update { !it }
    }

    fun setTagNotRegistered(isVisible: Boolean) {
        _isNotTagRegistered.value = isVisible
    }

    suspend fun getSize() {
        _tagListSize.value = getNfcTagUseCase().size
        //  println("TAG " + _tagListSize.value)
    }

    fun getStatus() {
        _isClosed.value = getPreferencesNfcStatusUseCase.invoke()
        println("STATUS " + _isClosed.value.toString())
    }

    fun setStatus(isClosed: Boolean) {
        saveNfcPreferencesStatusUseCase.invoke(isClosed)
        _isClosed.value = isClosed

    }

    fun getStatusChanged() {
        val newStatus = !_isClosed.value
        saveNfcPreferencesStatusUseCase.invoke(newStatus)
        _isClosed.update { newStatus }
    }

}
