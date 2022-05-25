package com.sample.someadapter.presentation.plants

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.ppav.someadapter.SomeDiffCallback
import com.github.ppav.someadapter.SomeHolder
import com.github.ppav.someadapter.delegate.DragAndDropHolderListener
import com.sample.someadapter.R
import com.sample.someadapter.domain.Plant.Fruit

class FruitHolder(
  view: View,
  private val onItemClick: (item: Fruit) -> Unit,
  private val onCounterClick: (item: Fruit) -> Unit,
) : SomeHolder<Fruit>(view), DragAndDropHolderListener {

  private object FruitCountPayload

  companion object {
    const val layoutId = R.layout.fragment_plants_fruit_holder

    val diffCallback = object : SomeDiffCallback<Fruit>() {
      override fun getChangePayload(
        oldItem: Fruit,
        newItem: Fruit
      ): Any? {
        return if (oldItem.name == newItem.name && oldItem.count != newItem.count) FruitCountPayload
        else null
      }
    }
  }

  private val imageView = itemView.findViewById<ImageView>(R.id.image)
  private val nameView = itemView.findViewById<TextView>(R.id.name)
  private val counterView = itemView.findViewById<TextView>(R.id.counter)
  override var onMoveStarted = {}
  override var onMoveFinish = {}

  override fun bind(
    data: Fruit,
    position: Int
  ) {
    counterView.setOnClickListener { onCounterClick.invoke(data) }
    itemView.setOnClickListener { onItemClick.invoke(data) }
    bindData(data)
    bindCount(data)

    onMoveStarted = {
      Glide.with(itemView.context)
          .load(
              "https://cdn5.vectorstock.com/i/thumb-large/56/34/cartoon-black-cat-back-view-red-bloody-claws-vector-21005634.jpg"
          )
          .into(imageView)
    }

    onMoveFinish = { bindData(data) }

  }

  override fun bind(
    data: Fruit,
    position: Int,
    payloads: List<Any>
  ) {
    counterView.setOnClickListener { onCounterClick.invoke(data) }
    itemView.setOnClickListener { onItemClick.invoke(data) }
    when (payloads.firstOrNull()) {
      FruitCountPayload -> bindCount(data)
      else -> super.bind(data, position, payloads)
    }
  }

  private fun bindData(data: Fruit) {
    nameView.text = data.name
    Glide
        .with(itemView.context)
        .load(data.photo)
        .into(imageView)
  }

  private fun bindCount(data: Fruit) {
    counterView.text = data.count.toString()
  }

}