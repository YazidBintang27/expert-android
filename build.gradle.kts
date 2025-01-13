// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
   dependencies {
      classpath(libs.navigation.safe.args.gradle.plugin)
   }
}

plugins {
   alias(libs.plugins.android.application) apply false
   alias(libs.plugins.kotlin.android) apply false
   alias(libs.plugins.android.library) apply false
   alias(libs.plugins.android.dynamic.feature) apply false
   id("com.google.devtools.ksp") version "2.0.10-1.0.24" apply false
}