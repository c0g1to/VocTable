package com.shket.voctable.vocabulary

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import java.lang.IllegalArgumentException

class VocabularyViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToDictionary = MutableLiveData<Boolean>(false)
    val navigateToDictionary: LiveData<Boolean>
        get() = _navigateToDictionary

    private val _navigateToExam = MutableLiveData<Boolean>(false)
    val navigateToExam: LiveData<Boolean>
        get() = _navigateToExam

    fun onDictionaryClicked() {
        _navigateToDictionary.value = true
    }

    fun onTestClicked() {
        _navigateToExam.value = true
    }

    fun doneDictionaryNavigating() {
        _navigateToDictionary.value = false
    }

    fun doneTestNavigating() {
        _navigateToExam.value = false
    }
}

class VocabularyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VocabularyViewModel::class.java)) {
            return VocabularyViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}