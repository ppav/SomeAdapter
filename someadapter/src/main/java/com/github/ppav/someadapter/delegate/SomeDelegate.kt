package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.RecyclerView

abstract class SomeDelegate {

  internal var itemsProvider: (() -> MutableList<Any>) = { arrayListOf() }

  open fun onAttachRecyclerView(recyclerView: RecyclerView) {}
  open fun onDetachedRecyclerView(recyclerView: RecyclerView) {}

}

