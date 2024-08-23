package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository

class AddNfcTagUseCase(private val repository: NfcTagRepository) {
    suspend operator fun invoke(tagText: String) {
        val tagText = NfcTag(tag = tagText)
        repository.insertTagNfc(tagText)
    }
}