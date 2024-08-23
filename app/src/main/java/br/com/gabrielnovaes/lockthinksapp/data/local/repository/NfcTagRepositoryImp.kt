package br.com.gabrielnovaes.lockthinksapp.data.local.repository

import br.com.gabrielnovaes.lockthinksapp.data.local.dao.NfcTagDao
import br.com.gabrielnovaes.lockthinksapp.data.local.entities.NfcTagEntity
import br.com.gabrielnovaes.lockthinksapp.domain.model.NfcTag
import br.com.gabrielnovaes.lockthinksapp.domain.repository.NfcTagRepository
import javax.inject.Inject


class NfcTagRepositoryImp @Inject constructor(private val nfcTagDao: NfcTagDao) : NfcTagRepository {
    override suspend fun insertTagNfc(nfcTag: NfcTag) {
        nfcTagDao.insertText(NfcTagEntity(tag = nfcTag.tag))
    }

    override suspend fun getNfcTag(): List<NfcTag> {
        return nfcTagDao.getNfcTag().map { NfcTag(it.id, it.tag) }
    }
}