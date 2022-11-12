plugins {
  id("com.android.application")
  kotlin("android")
}

dependencies {
  implementation(project(":someadapter"))
  implementation("com.google.android.material:material:1.7.0")
  implementation("com.github.bumptech.glide:glide:4.13.2")
  annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

}

android {
  compileSdk = 32
  defaultConfig {
    applicationId = "com.sample.someadapter"
    minSdk = 21
    targetSdk = 32
    versionCode = 1
    versionName = "1.0"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
//      proguardFiles  =  getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'

    }
  }

  buildFeatures {
    viewBinding = true
  }
}
