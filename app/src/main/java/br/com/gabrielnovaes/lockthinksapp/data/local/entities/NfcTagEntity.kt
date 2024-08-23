package br.com.gabrielnovaes.lockthinksapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tags")
data class NfcTagEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val tag: String = ""
)