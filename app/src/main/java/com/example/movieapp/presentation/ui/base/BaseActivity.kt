package com.example.movieapp.presentation.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.MovieApplication
import com.example.movieapp.di.presentation.activity.ActivitySubComponent
import com.example.movieapp.utility.LanguageManager

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

  private val activitySubComponent: ActivitySubComponent by lazy {
    MovieApplication.get(this).appComponent.getActivitySubComponent().bindsContext(this).build()
  }

  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(LanguageManager.attach(newBase, LanguageManager.getLanguage(newBase)))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    LanguageManager.attach(this)
    super.onCreate(savedInstanceState)
    setupInjection(activitySubComponent)
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    setupToolbar()
    afterViewsInstantiated(savedInstanceState)
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
    LanguageManager.attach(this)
  }

  override fun onSupportNavigateUp(): Boolean {
    super.onBackPressed()
    return true
  }

  override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
    if (overrideConfiguration != null) {
      val uiMode = overrideConfiguration.uiMode
      overrideConfiguration.setTo(baseContext.resources.configuration)
      overrideConfiguration.uiMode = uiMode
    }
    super.applyOverrideConfiguration(overrideConfiguration)
  }

  protected open fun setupInjection(component: ActivitySubComponent) {}
  protected open fun setupToolbar() {}

  protected abstract fun afterViewsInstantiated(savedInstanceState: Bundle?)
}