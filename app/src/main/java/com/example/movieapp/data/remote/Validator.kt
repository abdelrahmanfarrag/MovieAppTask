package com.example.movieapp.data.remote

import com.example.movieapp.presentation.common.Resource
import com.example.movieapp.presentation.common.ResourceState.ERROR
import com.example.movieapp.presentation.common.ResourceState.SUCCESS
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class Validator @Inject constructor(private val jsonResponse: Gson) {

  fun <T> validateApiResponse(response: Response<T>?): Resource<T> {
    response?.let {
      it.apply {
        if (isSuccessful) {
          val model = response.body()
          if (model != null) {
            modelToJson(model)
            return Resource(SUCCESS,data = model)
          }
        } else {
          return Resource(ERROR, message = com.example.movieapp.utility.Error.GENERAL)
        }
      }

    }
    return Resource(ERROR, message = com.example.movieapp.utility.Error.GENERAL)
  }

  private fun modelToJson(obj: Any): String {
    return jsonResponse.toJson(obj)
  }


}
