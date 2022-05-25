package com.github.ppav.someadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ppav.someadapter.SomeAdapter.Builder
import com.github.ppav.someadapter.delegate.SomeDelegate

inline fun <reified D, reified VB> Builder.withItem(
  noinline bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB,
  diffCallback: SomeDiffCallback<D> = SomeDiffCallback(),
  noinline holderCreator: (VB) -> SomeHolder<D>,
) = this.apply {
  ItemBinder(D::class.java, diffCallback) { viewGroup ->
    LayoutInflater.from(viewGroup.context)
        .let { bindingInflater.invoke(it, viewGroup, false) }
        .run(holderCreator::invoke)
  }.run(::addItem)
}

inline fun <reified D> Builder.withItem(
  res: Int,
  diffCallback: SomeDiffCallback<D> = SomeDiffCallback(),
  noinline holderCreator: (View) -> SomeHolder<D>,
) = this.apply {
  ItemBinder(D::class.java, diffCallback) {
    LayoutInflater.from(it.context)
        .inflate(res, it, false)
        .run(holderCreator::invoke)
  }.run(::addItem)
}

inline fun <reified D> Builder.withItem(
  diffCallback: SomeDiffCallback<D> = SomeDiffCallback(),
  noinline holderCreator: (ViewGroup) -> SomeHolder<D>,
) = this.apply {
  ItemBinder(D::class.java, diffCallback) { holderCreator.invoke(it) }
      .run(::addItem)
}

fun Builder.withDelegate(delegate: SomeDelegate) = this.apply { addDelegate(delegate) }



