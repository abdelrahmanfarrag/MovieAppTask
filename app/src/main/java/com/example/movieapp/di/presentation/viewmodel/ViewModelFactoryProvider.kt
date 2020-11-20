package com.example.movieapp.di.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */

class ViewModelFactoryProvider @Inject constructor(
  private val viewModelMap: Map<Class<out ViewModel>,
      @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    val creator = viewModelMap[modelClass] ?: viewModelMap.asIterable().firstOrNull { mapEntry ->
      modelClass.isAssignableFrom(mapEntry.key)
    }?.value ?: throw IllegalArgumentException()
    return try {
      @Suppress("UNCHECKED_CAST")
      creator.get() as T
    } catch (e: Exception) {
      throw IllegalArgumentException()
    }
  }
}