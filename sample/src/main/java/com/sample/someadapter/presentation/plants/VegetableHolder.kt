package com.sample.someadapter.presentation.plants

import com.bumptech.glide.Glide
import com.github.ppav.someadapter.SomeDiffCallback
import com.github.ppav.someadapter.SomeHolder
import com.sample.someadapter.databinding.FragmentPlantsVegetableHolderBinding
import com.sample.someadapter.domain.Plant.Vegetable

class VegetableHolder(
  private val viewBinding: FragmentPlantsVegetableHolderBinding,
  private val onItemClick: (item: Vegetable) -> Unit,
  private val onCounterClick: (item: Vegetable) -> Unit,
) :
    SomeHolder<Vegetable>(viewBinding.root) {

  private object VegetableCountPayload
  companion object {

    val diffCallback = object : SomeDiffCallback<Vegetable>() {
      override fun getChangePayload(
        oldItem: Vegetable,
        newItem: Vegetable
      ): Any? {
        return if (oldItem.name == newItem.name && oldItem.count != newItem.count) VegetableCountPayload
        else null
      }
    }
  }

  override fun bind(
    data: Vegetable,
    position: Int
  ) {
    itemView.setOnClickListener { onItemClick.invoke(data) }
    viewBinding.counter.setOnClickListener { onCounterClick.invoke(data) }
    bindData(data)
    bindCount(data)

  }

  override fun bind(
    data: Vegetable,
    position: Int,
    payloads: List<Any>
  ) {
    itemView.setOnClickListener { onItemClick.invoke(data) }
    viewBinding.counter.setOnClickListener { onCounterClick.invoke(data) }
    when (payloads.firstOrNull()) {
      VegetableCountPayload -> bindCount(data)
      else -> super.bind(data, position, payloads)
    }
  }

  private fun bindData(data: Vegetable) {
    viewBinding.name.text = data.name
    Glide
        .with(itemView.context)
        .load(data.photo)
        .into(viewBinding.image)
  }

  private fun bindCount(data: Vegetable) {
    viewBinding.counter.text = data.count.toString()
  }

}