package br.com.gabrielnovaes.lockthinksapp.domain.usecase

import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import javax.inject.Inject


class GetPreferencesNfcStatusUseCase @Inject constructor(
    private val nfcTagPreferencesRepository: NfcTagPreferencesRepository
) {
    operator fun invoke(): Boolean {
        return nfcTagPreferencesRepository.getStatus()
    }
}