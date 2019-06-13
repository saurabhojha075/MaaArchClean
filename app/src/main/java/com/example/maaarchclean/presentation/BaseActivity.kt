package com.example.maaarchclean.presentation

import android.support.v7.app.AppCompatActivity
import com.example.maaarchclean.BaseApplication
import com.example.maaarchclean.di.application.ApplicationComponent
import com.example.maaarchclean.di.screen.ScreenModule

open class BaseActivity : AppCompatActivity() {

  val screenComponent by lazy {
    getApplicationComponent().plus(ScreenModule(this))
  }

  private fun getApplicationComponent(): ApplicationComponent {
    return (application as BaseApplication).component
  }
}