package com.sample.someadapter.domain

import com.sample.someadapter.domain.Plant.Fruit
import com.sample.someadapter.domain.Plant.Vegetable

object PlantStorage {

  private val fruits = listOf(
      "Apple" to "https://www.applesfromny.com/wp-content/uploads/2020/05/Jonagold_NYAS-Apples2.png",
      "Banana" to "https://static.wikia.nocookie.net/fnaf-fanon-animatronics/images/4/40/%D0%91%D0%B0%D0%BD%D0%B0%D0%BD.png/revision/latest?cb=20190614113143&path-prefix=ru",
      "Apricot" to "https://cdn.store-factory.com/www.culinaide.com/content/product_1256642b.jpg?v=1596717830",
      "Pear" to "https://nicliquid.com/184-large_default/pear-flavor-concentrate.jpg",
      "Nectarine" to "https://static.libertyprim.com/files/familles/nectarine-large.jpg?1569271810",
      "Peach" to "https://cdn.hinative.com/attached_images/111292/13193123d9128dddf00310449757e10709cc23a2/large.jpg?1496063588",
  ).map { Fruit(it.first, it.second) }

  private val vegetables = listOf(
      "Beet" to "https://media.istockphoto.com/photos/beetroot-plant-with-four-green-leaves-picture-id177545726",
      "Onion" to "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Onion_on_White.JPG/1200px-Onion_on_White.JPG",
      "Potato" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUIBVb3YCXhlaA9fEfA52LFuMBUnk0afWSkw&usqp=CAU",
      "Celery" to "https://i5.walmartimages.ca/images/Enlarge/094/529/6000200094529.jpg",
      "Carrot" to "https://www.sansafe.ru/wp-content/uploads/2019/12/p9.jpg",
  ).map { Vegetable(it.first, it.second) }

  val data = listOf(
      fruits[0],
      vegetables[0],
      fruits[1],
      vegetables[1],
      fruits[2],
      vegetables[2],
      fruits[3],
      vegetables[3],
      fruits[4],
      vegetables[4],
  )


}
