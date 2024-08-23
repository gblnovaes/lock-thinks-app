package br.com.gabrielnovaes.lockthinksapp.domain.repository

import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag

interface NfcTagRepository {
    suspend fun insertTagNfc(nfcTag: NfcTag)
    suspend fun getNfcTag(): List<NfcTag>
}