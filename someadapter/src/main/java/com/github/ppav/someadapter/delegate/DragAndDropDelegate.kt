package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.ppav.someadapter.delegate.DragAndDropTouchCallback.Listener
import com.github.ppav.someadapter.delegate.DragAndDropTouchCallback.Listener.Companion.FINISH
import com.github.ppav.someadapter.delegate.DragAndDropTouchCallback.Listener.Companion.START
import java.util.Collections

interface DragAndDropHolderListener {
  var onMoveStarted: (() -> Unit)
  var onMoveFinish: (() -> Unit)
}

class DragAndDropResult(
  val item: Any,
  val from: Int,
  val to: Int
)

class DragAndDropDelegate(
  private val isDragEnabled: (Any, Int) -> Boolean = { _, _ -> true },
  private val callback: (result: DragAndDropResult) -> Unit,
) : SomeDelegate() {

  private val touchHelper: DragAndDropTouchCallback = DragAndDropTouchCallbackDefault { position ->
    isDragEnabled.invoke(
        itemsProvider.invoke()[position], position
    )
  }

  override fun onAttachRecyclerView(recyclerView: RecyclerView) {

    var fromIndex = 0

    touchHelper.registerListener(object : Listener {
      override val onMove: (from: Int, to: Int) -> Unit = { from, to ->
        itemsProvider.invoke()
            .run {
              Collections.swap(this, from, to)
              recyclerView.adapter?.notifyItemMoved(from, to)
            }
      }
      override val onMoveStateChanged: (state: Int, position: Int) -> Unit =
        { state: Int, position: Int ->
          when (state) {
            START -> fromIndex = position
            FINISH -> DragAndDropResult(itemsProvider.invoke()[position], fromIndex, position)
                .run(callback::invoke)
          }
        }
    })

    ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView)
  }

}