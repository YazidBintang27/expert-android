package com.yazime.yazimeapp

import android.app.Application
import com.yazime.core.di.coreModule
//import com.yazime.favourite.di.favouriteModule
import com.yazime.yazimeapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
   override fun onCreate() {
      super.onCreate()
      startKoin {
         androidLogger()
         androidContext(this@App)
         modules(listOf(coreModule, appModule))
      }
   }
}