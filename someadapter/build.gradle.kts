plugins {
  id("com.android.library")
  kotlin("android")
  id("maven-publish")
  id("signing")
}


android {
  compileSdk = 31

  defaultConfig {
    minSdk = 21
    targetSdk = 31
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation("com.google.android.material:material:1.6.0")
  testImplementation("junit:junit:4.13.2")
}