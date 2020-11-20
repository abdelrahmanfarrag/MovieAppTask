package com.example.movieapp.di.presentation.activity

import androidx.lifecycle.ViewModel
import com.example.movieapp.di.presentation.scopes.ViewModelKey
import com.example.movieapp.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.movieapp.di.presentation.viewmodel.ViewModelProviderModule
import com.example.movieapp.presentation.ui.base.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
@Module(includes = [ViewModelProviderModule::class])
abstract class ActivityViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

}