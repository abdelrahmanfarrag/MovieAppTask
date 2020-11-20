package com.example.movieapp.presentation.ui.base.splash

import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.presentation.Layout
import com.example.movieapp.presentation.ui.base.BaseActivity
import com.example.movieapp.presentation.ui.base.main.MainActivity
import com.example.movieapp.utility.LanguageManager
import com.example.movieapp.utility.extensions.startActivityNewTask
import kotlinx.android.synthetic.main.activity_splash.arabicButton
import kotlinx.android.synthetic.main.activity_splash.englishButton

@Layout(R.layout.activity_splash)
class SplashActivity : BaseActivity() {

  private fun arabicClickListener() {
    arabicButton.setOnClickListener {
      LanguageManager.setLanguage(this, LanguageManager.AR)
      launchActivity(MainActivity::class.java)
    }
  }

  private fun englishClickListener() {
    englishButton.setOnClickListener {
      LanguageManager.setLanguage(this, LanguageManager.EN)
      launchActivity(MainActivity::class.java)


    }
  }

  private fun launchActivity(activity: Class<*>, extras: Bundle? = null) {
    startActivityNewTask(activity, extras)
    finish()
  }

  override fun afterViewsInstantiated(savedInstanceState: Bundle?) {
    arabicClickListener()
    englishClickListener()
  }
}
