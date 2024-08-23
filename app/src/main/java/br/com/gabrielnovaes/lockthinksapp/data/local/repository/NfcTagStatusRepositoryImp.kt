package br.com.gabrielnovaes.lockthinksapp.data.local.repository

import br.com.gabrielnovaes.lockthinksapp.data.local.NfcTagPreferencesImp
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import javax.inject.Inject

class NfcTagStatusRepositoryImp @Inject constructor(private val nfcTagPreferencesImp: NfcTagPreferencesImp) :
    NfcTagPreferencesRepository {
    override fun saveStatus(isClosed: Boolean) {
        nfcTagPreferencesImp.saveStatus(isClosed)
    }

    override fun getStatus(): Boolean {
        return nfcTagPreferencesImp.getStatus()
    }
}