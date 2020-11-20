package com.example.movieapp.di.application.module

import com.example.movieapp.data.remote.api.Api
import com.example.movieapp.di.application.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

  @Provides
  @ApplicationScope fun providesMovieAp(
    retrofit: Retrofit
  ): Api = retrofit.create(Api::class.java)
}