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
  implementation("com.google.android.material:material:1.6.1")
  testImplementation("junit:junit:4.13.2")
}

val sourceJar by tasks.registering(Jar::class) {
  from(android.sourceSets["main"].java.srcDirs().srcDirs)
  archiveClassifier.set("sources")
}

publishing {
  publications {
    create<MavenPublication>("release") {
      artifact(sourceJar.get())
      artifact("$buildDir/outputs/aar/${artifactId}-${name}.aar")
    }

    create<MavenPublication>("debug") {
      artifact(sourceJar.get())
      artifact("$buildDir/outputs/aar/${artifactId}-${name}.aar")
    }
  }
}