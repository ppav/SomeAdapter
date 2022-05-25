package com.github.ppav.someadapter

open class SomeDiffCallback<in D> {
  @Suppress("UNCHECKED_CAST")
  internal fun areContentsTheSame(
    oldItem: Any,
    newItem: Any
  ): Boolean {
    return areContentsTheSame(oldItem as D, newItem as D)
  }

  @Suppress("UNCHECKED_CAST")
  internal fun getChangePayload(
    oldItem: Any,
    newItem: Any
  ): Any? {
    return getChangePayload(oldItem as D, newItem as D)
  }

  open fun areContentsTheSame(
    oldItem: D,
    newItem: D
  ): Boolean {
    return oldItem == newItem
  }

  open fun getChangePayload(
    oldItem: D,
    newItem: D
  ): Any? {
    return null
  }
}