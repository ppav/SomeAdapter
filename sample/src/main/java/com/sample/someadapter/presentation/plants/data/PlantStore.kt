package com.sample.someadapter.presentation.plants.data

import com.sample.someadapter.domain.Plant
import com.sample.someadapter.domain.PlantStorage

class PlantStore {

  enum class Soring {
    SORTED,
    MIXED;

    fun other(): Soring = when (this) {
      SORTED -> MIXED
      MIXED -> SORTED
    }
  }

  private var state: List<Plant> = PlantStorage.data

  var onStateChanged: (state: List<Plant>) -> Unit = { }
    set(value) {
      field = value
      field.invoke(state)
    }

  fun setAction(action: PlantAction) {
    state = plantReducer(action, state)
    onStateChanged.invoke(state)
  }

}