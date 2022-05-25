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

///
//plugins {
//  id 'com.android.application'
//  id 'org.jetbrains.kotlin.android'
//}
//
//android {
//  compileSdk 31
//
//  defaultConfig {
//    applicationId "com.sample.someadapter"
//    minSdk 21
//    targetSdk 31
//    versionCode 1
//    versionName "1.0"
//
//    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//  }
//
//  buildTypes {
//    release {
//      minifyEnabled false
//      proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//    }
//  }
//  compileOptions {
//    sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//  }
//  kotlinOptions {
//    jvmTarget = '1.8'
//  }
//
//  buildFeatures {
//    viewBinding true
//  }
//}
//
//dependencies {
//
//  implementation project(":someadapter")
//  implementation 'androidx.core:core-ktx:1.7.0'
//  implementation 'com.google.android.material:material:1.6.0'
//  implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
//  implementation "androidx.navigation:navigation-fragment-ktx:2.4.2"
//  implementation "androidx.navigation:navigation-ui-ktx:2.4.2"
//  implementation 'com.github.bumptech.glide:glide:4.13.0'
//  annotationProcessor 'com.github.bumptech.glide:compiler:4.13.0'
//
//  implementation "androidx.recyclerview:recyclerview:1.2.1"
//  testImplementation 'junit:junit:4.13.2'
//  androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//  androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//}