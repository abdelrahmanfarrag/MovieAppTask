package com.example.movieapp.di.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
@Module
abstract class ViewModelProviderModule {
  @Binds
  abstract fun bindsViewModelFactory(viewModelFactoryProvider: ViewModelFactoryProvider): ViewModelProvider.Factory
}