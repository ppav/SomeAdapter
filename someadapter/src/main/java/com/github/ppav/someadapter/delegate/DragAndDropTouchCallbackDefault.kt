package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.ppav.someadapter.delegate.DragAndDropTouchCallback.Listener.Companion.FINISH
import com.github.ppav.someadapter.delegate.DragAndDropTouchCallback.Listener.Companion.START

internal class DragAndDropTouchCallbackDefault : DragAndDropTouchCallback() {

  private var listener: Listener? = null

  override fun registerListener(listener: Listener) {
    this.listener = listener
  }

  override fun getMovementFlags(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder
  ): Int {
    return if (recyclerView.layoutManager is GridLayoutManager) {
      ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    } else {
      ItemTouchHelper.UP or ItemTouchHelper.DOWN
    }.let { makeMovementFlags(it, 0) }
  }

  override fun onMove(
    recyclerView: RecyclerView,
    source: ViewHolder,
    target: ViewHolder
  ): Boolean {
    listener?.onMove?.invoke(source.adapterPosition, target.adapterPosition)
    return true
  }

  override fun onSwiped(
    viewHolder: ViewHolder,
    direction: Int
  ) = Unit

  override fun onSelectedChanged(
    viewHolder: ViewHolder?,
    actionState: Int
  ) {
    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
      (viewHolder as? DragAndDropHolderListener)?.onMoveStarted?.invoke()

      viewHolder?.adapterPosition
          ?.run { listener?.onMoveStateChanged?.invoke(START, this) }
    }
    super.onSelectedChanged(viewHolder, actionState)
  }

  override fun clearView(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder
  ) {
    super.clearView(recyclerView, viewHolder)
    (viewHolder as? DragAndDropHolderListener)?.onMoveFinish?.invoke()
    listener?.onMoveStateChanged?.invoke(FINISH, viewHolder.adapterPosition)
  }
}

