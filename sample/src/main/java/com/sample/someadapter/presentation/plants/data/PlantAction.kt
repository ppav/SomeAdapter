package com.sample.someadapter.presentation.plants.data

import com.sample.someadapter.domain.Plant
import com.sample.someadapter.presentation.plants.data.PlantStore.Soring

interface PlantAction {

  class MovePlant(val from:Int, val to: Int) : PlantAction
  class RemovePlant(val from:Int) : PlantAction
  class PlantDecrease(val plant: Plant) : PlantAction
  class PlantIncrease(val plant: Plant) : PlantAction
  class ChangeSorting(val sorting: Soring) : PlantAction
}