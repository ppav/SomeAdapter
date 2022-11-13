package com.sample.someadapter.presentation.plants

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ppav.someadapter.SomeAdapter
import com.github.ppav.someadapter.delegate.DragAndDropDelegate
import com.github.ppav.someadapter.delegate.SwipeToDismissDelegate
import com.github.ppav.someadapter.withDelegate
import com.github.ppav.someadapter.withItem
import com.sample.someadapter.R
import com.sample.someadapter.databinding.FragmentPlantsVegetableHolderBinding
import com.sample.someadapter.domain.Plant
import com.sample.someadapter.domain.Plant.Fruit
import com.sample.someadapter.presentation.plants.data.PlantAction.ChangeSorting
import com.sample.someadapter.presentation.plants.data.PlantAction.MovePlant
import com.sample.someadapter.presentation.plants.data.PlantAction.PlantDecrease
import com.sample.someadapter.presentation.plants.data.PlantAction.PlantIncrease
import com.sample.someadapter.presentation.plants.data.PlantAction.RemovePlant
import com.sample.someadapter.presentation.plants.data.PlantStore

class PlantsFragment : Fragment(R.layout.fragment_plants) {

  private val plantStore = PlantStore()
  private var sorting = PlantStore.Soring.MIXED

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    val onPlantClick: (item: Plant) -> Unit = { plantStore.setAction(PlantIncrease(it)) }
    val onCounterClick: (item: Plant) -> Unit = { plantStore.setAction(PlantDecrease(it)) }

    val plantAdapter = SomeAdapter.Builder()
        .withItem(FragmentPlantsVegetableHolderBinding::inflate, VegetableHolder.diffCallback)
        { VegetableHolder(it, onPlantClick, onCounterClick) }
        .withItem(FruitHolder.layoutId, FruitHolder.diffCallback)
        { FruitHolder(it, onPlantClick, onCounterClick) }
        .withDelegate(
            DragAndDropDelegate(isDragEnabled = { item, _ -> item is Fruit }) {
              plantStore.setAction(MovePlant(it.from, it.to))
            })
        .withDelegate(SwipeToDismissDelegate { plantStore.setAction(RemovePlant(it.from)) })
        .build()


    view.findViewById<View>(R.id.sorting)
        .setOnClickListener { changeSorting() }

    view.findViewById<RecyclerView>(R.id.recycler)
        .apply {
          layoutManager = LinearLayoutManager(requireContext())
          adapter = plantAdapter
        }

    plantStore.onStateChanged = { plantAdapter.submitList(it) }

  }

  private fun changeSorting() {
    sorting = sorting.other()
    plantStore.setAction(ChangeSorting(sorting))
  }

}