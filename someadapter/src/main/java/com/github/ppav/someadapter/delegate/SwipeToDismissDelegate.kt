package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface SwipeHolderListener {
  var onSwipeStarted: (() -> Unit)
  var onSwipeFinish: (() -> Unit)
}

class SwipeToDismissResult<T>(
  val item: T,
  val from: Int,
)

@Suppress("UNCHECKED_CAST")
class SwipeToDismissDelegate<T>(
  private val callback: (result: SwipeToDismissResult<T>) -> Unit,
) : SomeDelegate() {

  override fun onAttachRecyclerView(recyclerView: RecyclerView) {
    ItemTouchHelper(
        SwipeToDismissTouchCallback { position ->
          itemsProvider.invoke()
              .run {
                val item = get(position)
                removeAt(position)
                recyclerView.adapter?.notifyItemRangeRemoved(position, 1)
                callback.invoke(SwipeToDismissResult(item as T, position))
              }
        }
    ).attachToRecyclerView(recyclerView)
  }
}