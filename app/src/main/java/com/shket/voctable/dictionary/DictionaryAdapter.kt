package com.shket.voctable.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shket.voctable.database.DictionaryEntry
import com.shket.voctable.databinding.DictionaryItemBinding

class DictionaryAdapter() : ListAdapter<DictionaryEntry,
DictionaryAdapter.DictionaryEntryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryAdapter.DictionaryEntryViewHolder {
        return DictionaryEntryViewHolder(DictionaryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class DictionaryEntryViewHolder(private var binding: DictionaryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionaryEntry: DictionaryEntry) {
            binding.entry = dictionaryEntry
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: DictionaryAdapter.DictionaryEntryViewHolder, position: Int) {
        val dictionaryEntry = getItem(position)
        holder.bind(dictionaryEntry)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DictionaryEntry>() {
        override fun areItemsTheSame(oldItem: DictionaryEntry, newItem: DictionaryEntry): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DictionaryEntry, newItem: DictionaryEntry): Boolean {
            return oldItem.entryId == newItem.entryId
        }
    }


}