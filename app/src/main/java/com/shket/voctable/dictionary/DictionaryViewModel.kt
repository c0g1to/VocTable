package com.shket.voctable.dictionary

import android.app.Application
import androidx.lifecycle.*
import com.shket.voctable.database.DictionaryDatabaseDao
import com.shket.voctable.database.DictionaryEntry
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

const val textFile = "clothing:одежда\n" +
        "clotted:свернувшийся\n" +
        "clotty:запекшийся\n" +
        "cloud:облако\n" +
        "cloudberry:морошка\n" +
        "cloudburst:ливень\n" +
        "cloud-capped:закрытый облаками\n" +
        "cloud-drift:плывущие облака\n" +
        "cloudiness:облачность\n" +
        "cloudland:сказочная страна\n" +
        "cloudless:безоблачный\n" +
        "cloudlet:облачко\n" +
        "cloudscape:облачный пейзаж\n" +
        "cloudy:облачный\n" +
        "clough:ущелье"

class DictionaryViewModel(application: Application, val database: DictionaryDatabaseDao) :
    AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var entries: LiveData<List<DictionaryEntry>> = database.getAllEntries()

    init {
//        uiScope.launch {
//            withContext(Dispatchers.IO) {
//                database.clear()
//            }
//        }
        initializeDictionaryDatabase()
        initializeDictionaryEntries()
    }

    private fun initializeDictionaryDatabase() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (database.countEntries() == 0) {

                    val entryList = mutableListOf<DictionaryEntry>()
                    textFile.split("\n")
                        .forEach {
                            entryList.add(
                                DictionaryEntry(
                                    word = it.substringBefore(':'),
                                    translation = it.substringAfter(':')
                                )
                            )
                        }

                    database.insertAll(*entryList.toTypedArray())
                }
            }
        }
    }

    private fun initializeDictionaryEntries() {
        uiScope.launch {
            entries = database.getAllEntries()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

class DictionaryViewModelFactory(
    private val application: Application,
    private val dataSource: DictionaryDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DictionaryViewModel::class.java)) {
            return DictionaryViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}