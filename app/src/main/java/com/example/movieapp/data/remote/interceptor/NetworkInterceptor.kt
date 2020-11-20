package com.example.movieapp.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
abstract class NetworkInterceptor : Interceptor {

  companion object {
    const val NETWORK_ISSUE = "Network is not available"
  }

  abstract fun isInternetAvailable(): Boolean

  @Throws(IOException::class)
  override fun intercept(chain: Chain): Response {
    val request = chain.request()
    if (!isInternetAvailable()) throw IllegalStateException(NETWORK_ISSUE)
    return chain.proceed(request)
  }
}