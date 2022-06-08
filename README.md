[![Maven Central](https://img.shields.io/maven-central/v/io.github.ppav/someadapter)](https://repo1.maven.org/maven2/io/github/ppav) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## SomeAdapter
The simple adapter to render different type data in RecyclerView


## Download
```kotlin
dependencies {
  //...
  implementation("io.github.ppav:someadapter:${latest_version}")
}
```

## Usage

#### Adapter 


```kotlin
SomeAdapter.Builder()
  .withItem<Fruit>(FruitLayoutBinding::inflate) { binding -> FruitHolder(binding) }
  .withItem<Vegetable>(R.layout.vegetable_layout) { view -> VegetableHolder(view) }
  .build()
```


#### Holder
```kotlin
class FruitHolder(val binding: FruitLayoutBinding) : SomeHolder<Fruit>(binding.root) {

  override fun bind(
    data: Fruit,
    position: Int
  ) {
    //...
  }
}
```

#### Set items
```kotlin
adapter.submitList(items)
```
<img src="images/device-1.gif" width="40%">

##### DiffCallback
```kotlin
val fruitDiffCallback = object : SomeDiffCallback<Fruit>() {

  override fun getChangePayload(
    oldItem: Fruit,
    newItem: Fruit): Any? = // payload or null 

  open fun areContentsTheSame(
    oldItem: Fruit,
    newItem: Fruit
  ): Boolean = olderItem == newItem
}

SomeAdapter.Builder()
  ....
  .withItem<Fruit>(.., fruitDiffCallback) //... 
  .build()

```

#### Delegate

SwipeToDismiss and DragAndDrop

<img src="images/device-2.gif" width="40%">



```kotlin
SomeAdapter.Builder()
  ....
  .withDelegate(DragAndDropDelegate { result -> /*...*/ })    //DragAndDropResult(item, from, to) 
  .withDelegate(SwipeToDismissDelegate { result -> /*...*/ }) //SwipeToDismissResult(item, from)
  .build()

```


## License
```
MIT License

Copyright (c) 2022 (@ppav)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
