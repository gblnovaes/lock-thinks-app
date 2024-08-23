package br.com.gabrielnovaes.lockthinksapp.data.local

import android.content.SharedPreferences
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagPreferencesRepository
import javax.inject.Inject

class NfcTagPreferencesImp @Inject constructor(private val sharedPreferences: SharedPreferences) :
    NfcTagPreferencesRepository {

    companion object {
        private const val CLOSED_STATUS_KEY = "CLOSED_STATUS"
    }

    override fun saveStatus(isClosed: Boolean) {
        sharedPreferences.edit().putBoolean(CLOSED_STATUS_KEY, isClosed).apply()
    }

    override fun getStatus(): Boolean {
        return sharedPreferences.getBoolean(CLOSED_STATUS_KEY, false)
    }
}