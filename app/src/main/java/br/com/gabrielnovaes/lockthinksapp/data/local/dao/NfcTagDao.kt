package br.com.gabrielnovaes.lockthinksapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.gabrielnovaes.lockthinksapp.data.local.entities.NfcTagEntity

@Dao
interface NfcTagDao {
    @Insert
    suspend fun insertText(nfcTagEntity: NfcTagEntity)

    @Query("SELECT * FROM tags ORDER BY id DESC LIMIT 1")
    suspend fun getNfcTag(): List<NfcTagEntity>
}