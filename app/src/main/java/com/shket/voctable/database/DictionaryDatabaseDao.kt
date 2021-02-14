package com.shket.voctable.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DictionaryDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg entries: DictionaryEntry)

    @Update
    fun update(entry: DictionaryEntry)

    @Query("SELECT * FROM dictionary_entry_table WHERE entryId = :key")
    fun get(key: Long): DictionaryEntry?

    @Query("SELECT * FROM dictionary_entry_table")
    fun getAllEntries(): LiveData<List<DictionaryEntry>>

    @Query("SELECT COUNT(*) FROM dictionary_entry_table")
    fun countEntries(): Int

    @Query("DELETE FROM dictionary_entry_table")
    fun clear()
}