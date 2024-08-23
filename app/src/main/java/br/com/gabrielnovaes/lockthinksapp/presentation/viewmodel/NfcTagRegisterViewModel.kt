package br.com.gabrielnovaes.lockthinksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.AddNfcTagUseCase
import br.com.gabrielnovaes.lockthinksapp.domain.usecase.GetNfcTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NfcTagRegisterViewModel @Inject constructor(
    private val addNfcTagUseCase: AddNfcTagUseCase,
    private val getNfcTagUseCase: GetNfcTagUseCase
) : ViewModel() {

    private val _tagText = MutableStateFlow<List<NfcTag>>(emptyList())
    val tag: StateFlow<List<NfcTag>> = _tagText

    fun addText(text: String) {
        viewModelScope.launch {
            addNfcTagUseCase(text)
            loadTexts()
        }
    }


    fun loadTexts() {
        viewModelScope.launch {
            _tagText.value = getNfcTagUseCase()
            println("TAG" + _tagText.value)
        }
    }
}
