package com.github.ppav.someadapter.delegate

import androidx.recyclerview.widget.ItemTouchHelper.Callback

abstract class DragAndDropTouchCallback : Callback() {

  abstract fun registerListener(listener: Listener)

  interface Listener {
    companion object {
      const val START = 0
      const val FINISH = 1
    }

    val onMove: (from: Int, to: Int) -> Unit
    val onMoveStateChanged: (state: Int, position: Int) -> Unit
  }

}