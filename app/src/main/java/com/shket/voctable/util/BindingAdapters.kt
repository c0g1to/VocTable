package com.shket.voctable.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shket.voctable.database.DictionaryEntry
import com.shket.voctable.dictionary.DictionaryAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<DictionaryEntry>?) {
    val adapter = recyclerView.adapter as DictionaryAdapter
    adapter.submitList(data)
}