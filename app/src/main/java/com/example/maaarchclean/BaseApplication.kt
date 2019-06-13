package com.example.maaarchclean

import android.app.Application
import com.example.maaarchclean.di.application.ApplicationComponent
import com.example.maaarchclean.di.application.ApplicationModule
import com.example.maaarchclean.di.application.DaggerApplicationComponent

class BaseApplication : Application() {

  lateinit var component: ApplicationComponent
  override fun onCreate() {
    super.onCreate()
    inject()
  }

  fun inject() {
    component = DaggerApplicationComponent.builder().applicationModule(
        ApplicationModule(this)
    ).build()
    component.inject(this)
  }
}