package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface SwipeHolderListener {
  var onSwipeStarted: (() -> Unit)
  var onSwipeFinish: (() -> Unit)
}

class SwipeToDismissResult(
  val item: Any,
  val from: Int,
)

class SwipeToDismissDelegate(
  private val isSwipeEnabled: (Any, Int) -> Boolean = { _, _ -> true },
  private val callback: (result: SwipeToDismissResult) -> Unit,
) : SomeDelegate() {

  override fun onAttachRecyclerView(recyclerView: RecyclerView) {
    ItemTouchHelper(
        SwipeToDismissTouchCallback(
            isSwipeEnabled = { position ->
              isSwipeEnabled.invoke(
                  itemsProvider.invoke()[position], position
              )
            }
        ) { position ->
          itemsProvider.invoke()
              .run {
                val item = get(position)
                removeAt(position)
                recyclerView.adapter?.notifyItemRangeRemoved(position, 1)
                callback.invoke(SwipeToDismissResult(item, position))
              }
        }
    ).attachToRecyclerView(recyclerView)
  }
}