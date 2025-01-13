plugins {
   alias(libs.plugins.android.dynamic.feature)
   alias(libs.plugins.kotlin.android)
   id("androidx.navigation.safeargs")
}
android {
   namespace = "com.yazime.yazimeapp.favourite"
   compileSdk = 34

   defaultConfig {
      minSdk = 24
      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildTypes {
      release {
         isMinifyEnabled = false
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
   implementation(project(":app"))
   implementation(project(":core"))
   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}