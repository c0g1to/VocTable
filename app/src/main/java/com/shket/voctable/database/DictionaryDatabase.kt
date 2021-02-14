package com.shket.voctable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DictionaryEntry::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract val dictionaryDatabaseDao: DictionaryDatabaseDao

    companion object {

        private lateinit var INSTANCE: DictionaryDatabase

        fun getDatabase(context: Context): DictionaryDatabase {
            synchronized(DictionaryDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DictionaryDatabase::class.java,
                        "dictionary_database"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}