package com.github.ppav.someadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig.Builder
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.ppav.someadapter.delegate.DelegateComposition
import com.github.ppav.someadapter.delegate.SomeDelegate

class SomeAdapter internal constructor(
  private val binders: List<ItemBinder<*>>,
  private val delegate: SomeDelegate
) :
    Adapter<ViewHolder>() {

  private val diffCallbackDelegate = object :
      DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(
      oldItem: Any,
      newItem: Any
    ) = (oldItem::class == newItem::class)

    override fun areContentsTheSame(
      oldItem: Any,
      newItem: Any
    ): Boolean = binders.first { viewBinder -> viewBinder.isType(oldItem) }
        .diffCallback.areContentsTheSame(oldItem, newItem)

    override fun getChangePayload(
      oldItem: Any,
      newItem: Any
    ): Any? = binders.first { viewBinder -> viewBinder.isType(oldItem) }
        .diffCallback.getChangePayload(oldItem, newItem)
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    delegate.onAttachRecyclerView(recyclerView)
  }

  override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
    delegate.onDetachedRecyclerView(recyclerView)
    super.onDetachedFromRecyclerView(recyclerView)
  }

  init {
    delegate.itemsProvider = { items }
  }

  private var items: MutableList<Any> = mutableListOf()

  fun submitList(
    list: List<Any>,
    commitCallback: Runnable? = null
  ) {
    items = list.toMutableList()
    differ.submitList(items, commitCallback)
  }

  private val differ by lazy {
    AsyncListDiffer(AdapterListUpdateCallback(this), Builder(diffCallbackDelegate).build())
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder = binders[viewType].createViewHolder(parent)

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) = with(differ.currentList[position]) {
    binders.first { viewBinder -> viewBinder.isType(this) }
        .bindView(holder, this, position)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int,
    payloads: List<Any>
  ) {
    with(differ.currentList[position]) {
      binders.first { viewBinder -> viewBinder.isType(this) }
          .bindView(holder, this, position, payloads)
    }

  }

  override fun getItemViewType(position: Int): Int {
    return binders.indexOfFirst { viewBinder -> viewBinder.isType(differ.currentList[position]) }
        .takeUnless { index -> index == -1 }
        ?: throw NoSuchElementException(
            "ItemBinder by (${differ.currentList[position]}) not found"
        )
  }

  override fun getItemCount(): Int = differ.currentList.size

  class Builder {
    private val binders: MutableList<ItemBinder<*>> = mutableListOf()
    private val delegates: MutableList<SomeDelegate> = mutableListOf()
    fun build() = SomeAdapter(binders, DelegateComposition(delegates))
    @PublishedApi internal fun addItem(binder: ItemBinder<*>) = this.apply { binders.add(binder) }
    @PublishedApi internal fun addDelegate(delegate: SomeDelegate) =
      this.apply { delegates.add(delegate) }
  }
}