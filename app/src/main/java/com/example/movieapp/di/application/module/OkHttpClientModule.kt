package com.example.movieapp.di.application.module

import com.example.movieapp.data.remote.interceptor.NetworkInterceptor
import com.example.movieapp.di.application.scope.ApplicationScope
import com.example.movieapp.utility.extensions.isNetworkAvailable
import android.app.Application
import com.example.movieapp.BuildConfig
import com.example.movieapp.utility.Constants
import com.example.movieapp.utility.Constants.ARGS.KEY.API_KEY
import com.example.movieapp.utility.Constants.ARGS.VALUES.API_KEY_VALUE
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */

@Module()
class OkHttpClientModule {

  @Provides
  @ApplicationScope fun providesGeneralOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkInterceptor: NetworkInterceptor
  ): OkHttpClient {
    return getOkHttpClientBuilder()
      .addInterceptor(networkInterceptor)
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @ApplicationScope fun providesInterceptUrlToAddApiKey() = object : Interceptor {
    override fun intercept(chain: Chain): Response {
      val url = chain
        .request()
        .url
        .newBuilder()
        .addQueryParameter(API_KEY, API_KEY_VALUE)
        .build()

      val request = chain.request()
        .newBuilder()
        .url(url)
        .build()
      return chain.proceed(request)
    }

  }


  @Provides
  @ApplicationScope fun getNetworkInterceptor(application: Application): NetworkInterceptor =
    object : NetworkInterceptor() {
      override fun isInternetAvailable(): Boolean = application.isNetworkAvailable()
    }

  @Provides
  @ApplicationScope fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    return logging
  }

  private fun getOkHttpClientBuilder(): Builder =
    Builder().connectTimeout(20, SECONDS)
      .readTimeout(30, SECONDS)
      .writeTimeout(20, SECONDS)
      .retryOnConnectionFailure(true)
      .addInterceptor { interceptSocketException(it) }


  @Throws(IOException::class) private fun interceptSocketException(chain: Chain): Response {
    val response = chain.proceed(chain.request())
    try {
      return response.newBuilder()
        .body(response.body?.string()?.toResponseBody(response.body?.contentType()))
        .build()
    } catch (exception: SocketTimeoutException) {
    }
    return response
  }
}