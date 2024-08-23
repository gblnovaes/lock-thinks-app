package br.com.gabrielnovaes.lockthinksapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gabrielnovaes.lockthinksapp.data.local.dao.NfcTagDao
import br.com.gabrielnovaes.lockthinksapp.data.local.entities.NfcTagEntity

@Database(entities = [NfcTagEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nfcTagDao(): NfcTagDao
}