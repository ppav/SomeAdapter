package com.sample.someadapter.presentation.plants.data

import com.sample.someadapter.domain.Plant
import com.sample.someadapter.presentation.plants.data.PlantAction.PlantIncrease
import com.sample.someadapter.presentation.plants.data.PlantAction.ChangeSorting
import com.sample.someadapter.presentation.plants.data.PlantAction.MovePlant
import com.sample.someadapter.presentation.plants.data.PlantAction.PlantDecrease
import com.sample.someadapter.presentation.plants.data.PlantAction.RemovePlant
import com.sample.someadapter.presentation.plants.data.PlantStore.Soring.MIXED
import com.sample.someadapter.presentation.plants.data.PlantStore.Soring.SORTED

fun plantReducer(
  action: PlantAction,
  oldState: List<Plant>
): List<Plant> {
  return when (action) {
    is PlantIncrease -> oldState
        .map { plant -> if (plant == action.plant) plant.copy(count = plant.count + 1) else plant }

    is PlantDecrease -> oldState
        .map { plant -> if (plant == action.plant) plant.copy(count = plant.count - 1) else plant }

    is ChangeSorting -> when (action.sorting) {
      MIXED -> oldState.shuffled()
      SORTED -> oldState.sortedBy { it.javaClass.simpleName }
    }

    is MovePlant -> oldState.toMutableList()
        .apply {
          val item = get(action.from)
          removeAt(action.from)
          add(action.to, item)
        }

    is RemovePlant -> oldState.toMutableList()
        .apply {
          removeAt(action.from)
        }
    else -> oldState.toList()
  }
}
