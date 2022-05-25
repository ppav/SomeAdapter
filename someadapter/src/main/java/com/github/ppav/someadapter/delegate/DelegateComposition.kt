package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.RecyclerView

internal class DelegateComposition(private val delegates: List<SomeDelegate>) : SomeDelegate() {

  init {
    delegates.forEach { it.itemsProvider = { itemsProvider.invoke() } }
  }

  override fun onAttachRecyclerView(recyclerView: RecyclerView) {
    delegates.forEach { it.onAttachRecyclerView(recyclerView) }
  }

  override fun onDetachedRecyclerView(recyclerView: RecyclerView) {
    delegates.forEach { it.onDetachedRecyclerView(recyclerView) }
  }
}