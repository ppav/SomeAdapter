package com.github.ppav.someadapter

import androidx.recyclerview.widget.RecyclerView
import com.github.ppav.someadapter.delegate.SomeDelegate

class SomeAdapterDelegate(
  binders: List<ItemBinder<*>>,
  private val delegates: List<SomeDelegate>)
  : SomeAdapter(binders) {

  private var items: MutableList<Any> = mutableListOf()

  init {
    delegates.forEach { it.itemsProvider = { items } }
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    delegates.forEach { it.onAttachRecyclerView(recyclerView) }
  }

  override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
    delegates.forEach { it.onDetachedRecyclerView(recyclerView) }
    super.onDetachedFromRecyclerView(recyclerView)
  }

  override fun submitList(
    list: List<Any>,
    commitCallback: Runnable?
  ) {
    items = list.toMutableList()
    super.submitList(items, commitCallback)
  }
}

