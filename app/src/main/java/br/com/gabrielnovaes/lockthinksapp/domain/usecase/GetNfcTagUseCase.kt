package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository

class GetNfcTagUseCase(private val repository: NfcTagRepository) {
    suspend operator fun invoke(): List<NfcTag> {
        return repository.getNfcTag()
    }
}