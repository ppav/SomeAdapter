package com.github.ppav.someadapter.delegate

import android.graphics.Canvas
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.Callback
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlin.math.abs

class SwipeToDismissTouchCallback(
  private val isSwipeEnabled: (Int) -> Boolean,
  private val onItemSwipe: (position: Int) -> Unit
) : Callback() {

  companion object {
    const val ALPHA_FULL = 1.0f
  }

  override fun getMovementFlags(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder
  ): Int {
    return if (recyclerView.layoutManager is GridLayoutManager) {
      0
    } else {
      ItemTouchHelper.START or ItemTouchHelper.END
    }
        .let { it.takeIf { isSwipeEnabled.invoke(viewHolder.bindingAdapterPosition) } ?: 0 }
        .let { makeMovementFlags(0, it) }
  }

  override fun onMove(
    recyclerView: RecyclerView,
    source: ViewHolder,
    target: ViewHolder
  ): Boolean {
    return false
  }

  override fun onSwiped(
    viewHolder: ViewHolder,
    direction: Int
  ) {
    onItemSwipe.invoke(viewHolder.bindingAdapterPosition)
  }

  override fun onChildDraw(
    c: Canvas,
    recyclerView: RecyclerView,
    viewHolder: ViewHolder,
    dX: Float,
    dY: Float,
    actionState: Int,
    isCurrentlyActive: Boolean
  ) {
    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      val alpha = ALPHA_FULL - abs(dX) / viewHolder.itemView.width.toFloat()
      viewHolder.itemView.alpha = alpha
      viewHolder.itemView.translationX = dX
    } else {
      super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
  }

  override fun onSelectedChanged(
    viewHolder: ViewHolder?,
    actionState: Int
  ) {
    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
      (viewHolder as? SwipeHolderListener)?.onSwipeStarted?.invoke()
    }
    super.onSelectedChanged(viewHolder, actionState)
  }

  override fun clearView(
    recyclerView: RecyclerView,
    viewHolder: ViewHolder
  ) {
    super.clearView(recyclerView, viewHolder)
    (viewHolder as? SwipeHolderListener)?.onSwipeFinish?.invoke()
  }
}
