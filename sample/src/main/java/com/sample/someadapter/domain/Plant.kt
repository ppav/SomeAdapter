package com.sample.someadapter.domain

sealed class Plant(
  val name: String,
  val photo: String,
  val count: Int
) {

  class Fruit(
    name: String,
    photo: String,
    count: Int = 0
  ) : Plant(name, photo, count)

  class Vegetable(
    name: String,
    photo: String,
    count: Int = 0
  ) : Plant(name, photo, count)

  fun copy(
    name: String = this.name,
    photo: String = this.photo,
    count: Int = this.count
  ) = when (this) {
    is Fruit -> Fruit(name, photo, count)
    is Vegetable -> Vegetable(name, photo, count)

  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Plant

    if (name != other.name) return false
    if (photo != other.photo) return false
    if (count != other.count) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + photo.hashCode()
    result = 31 * result + count
    return result
  }
}
