package com.example.movieapp.di.application.module

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */

import com.example.movieapp.data.remote.api.Urls.BASE_URL
import com.example.movieapp.data.remote.converter.NullOnEmptyConverterFactory
import com.example.movieapp.di.application.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [OkHttpClientModule::class])
class NetworkModule {
  @Provides
  @ApplicationScope fun providesGson(): Gson = GsonBuilder().setLenient().create()

  @Provides
  @ApplicationScope fun providesLiveScoreRetrofitInstance(
    client: OkHttpClient
    , gson: Gson
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(NullOnEmptyConverterFactory())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()
}
