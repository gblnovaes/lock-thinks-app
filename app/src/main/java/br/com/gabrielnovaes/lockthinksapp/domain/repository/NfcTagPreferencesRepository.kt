package br.com.gabrielnovaes.lockthinksapp.domain.repository

interface NfcTagPreferencesRepository {
    fun saveStatus(isClosed: Boolean)
    fun getStatus(): Boolean
}

