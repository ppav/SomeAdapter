plugins {
  id("com.android.application")
  kotlin("android")
}

dependencies {
  implementation(project(":someadapter"))
  implementation("com.google.android.material:material:1.6.0")
  implementation("com.github.bumptech.glide:glide:4.13.0")
  annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

}

android {
  compileSdk = 31
  defaultConfig {
    applicationId = "com.sample.someadapter"
    minSdk = 21
    targetSdk = 31
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
