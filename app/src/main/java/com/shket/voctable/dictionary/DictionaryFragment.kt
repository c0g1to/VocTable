package com.shket.voctable.dictionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shket.voctable.R
import com.shket.voctable.database.DictionaryDatabase
import com.shket.voctable.databinding.FragmentDictionaryBinding

class DictionaryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application

        val dataSource = DictionaryDatabase.getDatabase(application).dictionaryDatabaseDao

        val binding = FragmentDictionaryBinding.inflate(inflater)

        val viewModelFactory = DictionaryViewModelFactory(application, dataSource)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(DictionaryViewModel::class.java)
        binding.dictionaryViewModel = viewModel

        binding.dictionaryList.adapter = DictionaryAdapter()

        binding.lifecycleOwner = this

        return binding.root
    }
}