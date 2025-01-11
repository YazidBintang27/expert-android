plugins {
   alias(libs.plugins.android.library)
   alias(libs.plugins.kotlin.android)
   id("com.google.devtools.ksp")
   id("kotlin-parcelize")
   id ("androidx.navigation.safeargs")
}

android {
   namespace = "com.yazime.core"
   compileSdk = 35

   defaultConfig {
      minSdk = 24

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      consumerProguardFiles("consumer-rules.pro")
   }

   buildTypes {
      release {
         isMinifyEnabled = false
         proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
         )
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
      viewBinding = true
   }
}

dependencies {
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   api(libs.material)
   api(libs.androidx.constraintlayout)
   api(libs.junit)
   api(libs.androidx.junit)
   api(libs.androidx.espresso.core)

   implementation(libs.androidx.room.runtime)
   ksp(libs.androidx.room.compiler)
   implementation(libs.androidx.room.ktx)

   implementation(libs.retrofit)
   implementation(libs.converter.gson)

   api(libs.kotlinx.coroutines.android)
   api(libs.androidx.lifecycle.viewmodel.ktx)
   api(libs.androidx.lifecycle.livedata.ktx)
   api(libs.androidx.lifecycle.runtime.ktx)

   debugImplementation(libs.library)
   releaseImplementation(libs.library.no.op)

   api(libs.koin.android)

   api(libs.androidx.navigation.fragment)
   api(libs.androidx.navigation.ui)

   api(libs.androidx.navigation.dynamic.features.fragment)

   api(libs.glide)
   api(libs.lottie)
}