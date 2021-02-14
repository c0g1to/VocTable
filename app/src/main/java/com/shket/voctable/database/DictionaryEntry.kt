package com.shket.voctable.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_entry_table")
data class DictionaryEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0L,
    val word: String = "",
    val translation: String = ""
)