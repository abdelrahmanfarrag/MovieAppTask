package com.example.movieapp

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.movieapp.di.application.component.AppComponent
import com.example.movieapp.di.application.component.DaggerAppComponent

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
class MovieApplication : Application() {

  companion object {
    fun get(activity: FragmentActivity) = activity.application as MovieApplication
  }

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    setupInjection()
  }

  private fun setupInjection() {
    appComponent = DaggerAppComponent.builder()
      .bindApplication(this)
      .build()
  }

}
