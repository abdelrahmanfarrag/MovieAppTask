package com.example.movieapp.presentation.ui.base.main

import android.content.Context
import com.example.movieapp.data.model.Images
import com.example.movieapp.data.model.People
import com.example.movieapp.data.remote.Validator
import com.example.movieapp.data.remote.api.Api
import com.example.movieapp.data.remote.response.mapResponseToPeopleModel
import com.example.movieapp.data.remote.response.mapToImages
import com.example.movieapp.presentation.common.Resource
import com.example.movieapp.presentation.common.ResourceState.ERROR
import com.example.movieapp.presentation.common.ResourceState.SUCCESS
import com.example.movieapp.utility.Constants.ARGS.KEY.API_KEY
import com.example.movieapp.utility.Constants.ARGS.KEY.API_LANGUAGE
import com.example.movieapp.utility.Constants.ARGS.KEY.API_PAGE
import com.example.movieapp.utility.Constants.ARGS.VALUES.API_KEY_VALUE
import com.example.movieapp.utility.Error
import com.example.movieapp.utility.LanguageManager
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
class MainRepository @Inject constructor(
  private val api: Api,
  private val validator: Validator,
  private val context: Context
) {
  fun callPeopleService(page: Int): Single<Resource<People>> =
    api.loadPeople(providesQueriesToPeopleService(page))
      .map { validator.validateApiResponse(it) }
      .map { resource ->
        resource.data?.let { peopleResource ->
          Resource(SUCCESS, data = peopleResource.mapResponseToPeopleModel())
        } ?: Resource(ERROR, message = Error.GENERAL)
      }

  fun callPeopleDetailsService(id: Int): Single<Resource<Images>> =
    api.loadPeopleDetails(id)
      .map { validator.validateApiResponse(it) }
      .map { resource ->
        resource.data?.let { imagesResource ->
          Resource(SUCCESS, data = imagesResource.mapToImages())
        } ?: Resource(ERROR, message = Error.GENERAL)
      }

  private fun providesQueriesToPeopleService(page: Int): Map<String, String> {
    val map = HashMap<String, String>()
    map[API_LANGUAGE] = LanguageManager.getLanguage(context)
    map[API_PAGE] = page.toString()
    map[API_KEY] = API_KEY_VALUE
    return map
  }
}