package com.github.ppav.someadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class SomeHolder<D>(view: View) : ViewHolder(view) {

  abstract fun bind(
    data: D,
    position: Int
  )

  open fun bind(
    data: D,
    position: Int,
    payloads: List<Any>
  ) {
    bind(data, position)
  }
}