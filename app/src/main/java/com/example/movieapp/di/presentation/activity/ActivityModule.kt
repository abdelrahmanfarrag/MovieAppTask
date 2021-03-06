package com.example.movieapp.di.presentation.activity

import android.content.Context
import com.example.movieapp.data.remote.Validator
import com.example.movieapp.data.remote.api.Api
import com.example.movieapp.di.presentation.scopes.PerActivity
import com.example.movieapp.presentation.ui.base.main.MainRepository
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
@Module
class ActivityModule {

  @Provides
  @PerActivity
  fun providesMainRepository(
    api: Api,
    validator: Validator,
    context: Context
  ) = MainRepository(api, validator, context)
}
