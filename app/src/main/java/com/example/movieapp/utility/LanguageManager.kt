package com.example.movieapp.utility

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.example.movieapp.utility.Constants.PREFS
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
object LanguageManager {

  const val AR = "ar"
  const val EN = "en"
  private const val SAUDI_ARABIA = "SA"

  fun attach(context: Context): Context {
    val language = getLanguage(context)
    return setLocale(context, language)
  }

  fun attach(context: Context, defaultLanguage: String): Context {
    val language = getLanguage(context, defaultLanguage)
    return setLocale(context, language)
  }

  private fun getLanguage(context: Context, defaultLanguage: String): String {
    return getLanguageSharedPreferences(context).getString(PREFS.LANGUAGE.KEY.LANGUAGE, defaultLanguage) ?: EN
  }

  fun getLanguage(context: Context): String {
    return getLanguageSharedPreferences(context).getString(PREFS.LANGUAGE.KEY.LANGUAGE, "") ?: ""
  }

  fun getLanguageIndex(context: Context): Int {
    return when (getLanguage(context)) {
      AR -> 1
      else -> 0
    }
  }

  fun setLanguage(context: Context, language: String) {
    getLanguageSharedPreferences(context).edit().putString(PREFS.LANGUAGE.KEY.LANGUAGE, language).apply()
  }

  private fun getLanguageSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences(PREFS.LANGUAGE.NAME, Context.MODE_PRIVATE)

  private fun setLocale(context: Context, language: String): Context {
    setLanguage(context, language)
    return if (VERSION.SDK_INT >= VERSION_CODES.O) {
      updateResources(context, language)
    } else updateResourcesLegacy(context, language)
  }

  @TargetApi(VERSION_CODES.O) private fun updateResources(
    context: Context,
    language: String
  ): Context {
    val locale = createLocale(language)
    Locale.setDefault(locale)
    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    configuration.setLayoutDirection(locale)
    return context.createConfigurationContext(configuration)
  }

  @Suppress("DEPRECATION")
  private fun updateResourcesLegacy(context: Context, language: String): Context {
    val locale = createLocale(language)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = resources.configuration
    configuration.locale = locale
    configuration.setLayoutDirection(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
    return context
  }

  private fun createLocale(language: String): Locale = Locale(language, SAUDI_ARABIA)
}