package com.shket.voctable.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.shket.voctable.R
import com.shket.voctable.databinding.FragmentVocabularyBinding

class VocabularyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application

        val binding: FragmentVocabularyBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_vocabulary, container, false)

        val viewModelFactory = VocabularyViewModelFactory(application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(VocabularyViewModel::class.java)

        binding.vocabularyViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToDictionary.observe(this as LifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    VocabularyFragmentDirections.actionVocabularyFragmentToDictionaryFragment())
                viewModel.doneDictionaryNavigating()
            }
        })

        viewModel.navigateToExam.observe(this as LifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    VocabularyFragmentDirections.actionVocabularyFragmentToExamFragment()
                )
                viewModel.doneTestNavigating()
            }
        })

        return binding.root
    }
}