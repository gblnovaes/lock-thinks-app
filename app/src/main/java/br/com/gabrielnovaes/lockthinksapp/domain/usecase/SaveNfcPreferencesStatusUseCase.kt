package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import javax.inject.Inject


class SaveNfcPreferencesStatusUseCase @Inject constructor(
    private val nfcTagPreferencesRepository: NfcTagPreferencesRepository
) {
      operator fun invoke(status: Boolean) {
        return nfcTagPreferencesRepository.saveStatus(status)
    }
}