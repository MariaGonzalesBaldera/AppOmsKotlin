plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  id("kotlin-kapt")
  id("com.google.dagger.hilt.android")
  id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

android {
  namespace = "master_provider_else.reclamos"
  compileSdk = 34

  defaultConfig {
    applicationId = "master_provider_else.reclamos"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  //Room
  implementation("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
  //kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
  implementation("androidx.room:room-ktx:${rootProject.extra["room_version"]}")
  kapt("androidx.room:room-compiler:${rootProject.extra["room_version"]}")

  // Fragment
  implementation("androidx.fragment:fragment-ktx:1.8.0")
  // Activity
  implementation("androidx.activity:activity-ktx:1.9.0")
  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
  // LiveData
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")
  // Retrofitq
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  //Corrutinas
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
  //dagger-hilt
  implementation("com.google.dagger:hilt-android:2.51.1")
  kapt("com.google.dagger:hilt-android-compiler:2.51.1")
  //material
  implementation("androidx.compose.material:material:1.6.8")
  //navigation
  val nav_version = "2.7.7"
  implementation("androidx.navigation:navigation-compose:$nav_version")
//mapbox
  implementation("com.mapbox.maps:android:11.4.1")
  implementation("com.mapbox.extension:maps-compose:11.4.1")

  //getUbication
  implementation("com.google.android.gms:play-services-location:21.3.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
  implementation("com.google.android.gms:play-services-maps:18.1.0")

  // PERMISOS
  implementation("com.google.accompanist:accompanist-permissions:0.28.0")

  //coil
  implementation("io.coil-kt:coil-compose:1.4.0")
}