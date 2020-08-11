package com.studyfork.sfoide.presentation.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemsWithListAdapter")
fun <T, VH : RecyclerView.ViewHolder> setItemsWithListAdapter(
    recyclerView: RecyclerView,
    items: List<T>?
) {
    (recyclerView.adapter as? ListAdapter<T, VH>)?.let { adapter ->
        val newList = if (items == null || items.isEmpty()) null else ArrayList(items)
        adapter.submitList(newList)
        if (newList == null) {
            adapter.notifyDataSetChanged()
        }
    }
}