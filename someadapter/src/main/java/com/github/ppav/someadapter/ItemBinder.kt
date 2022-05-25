package com.github.ppav.someadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class ItemBinder<D>(
  private val type: Class<D>,
  internal val diffCallback: SomeDiffCallback<D> = SomeDiffCallback(),
  private val viewHolderCreator: (ViewGroup) -> SomeHolder<D>,
) {

  fun isType(any: Any): Boolean = any::class.java == type

  fun createViewHolder(
    viewGroup: ViewGroup
  ): SomeHolder<D> = viewHolderCreator.invoke(viewGroup)

  @Suppress("UNCHECKED_CAST")
  fun bindView(
    holder: ViewHolder,
    data: Any,
    position: Int,
    payloads: List<Any>
  ) {
    (holder as SomeHolder<D>).bind(data as D, position, payloads)
  }

  @Suppress("UNCHECKED_CAST")
  fun bindView(
    holder: ViewHolder,
    data: Any,
    position: Int,
  ) {
    (holder as SomeHolder<D>).bind(data as D, position)
  }
}

