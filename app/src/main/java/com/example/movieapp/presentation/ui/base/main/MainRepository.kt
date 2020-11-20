package com.example.movieapp.presentation.ui.base.main

import com.example.movieapp.data.model.People
import com.example.movieapp.data.remote.Validator
import com.example.movieapp.data.remote.api.Api
import com.example.movieapp.data.remote.response.mapResponseToPeopleModel
import com.example.movieapp.presentation.common.Resource
import com.example.movieapp.presentation.common.ResourceState.ERROR
import com.example.movieapp.presentation.common.ResourceState.SUCCESS
import com.example.movieapp.utility.Constants.ARGS.KEY.API_LANGUAGE
import com.example.movieapp.utility.Constants.ARGS.KEY.API_PAGE
import com.example.movieapp.utility.Error
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class MainRepository @Inject constructor(
  private val api: Api,
  private val validator: Validator
) {
  fun callPeopleService(page: Int): Single<Resource<People>> =
    api.loadPeople(providesQueriesToPeopleService(page))
      .map { validator.validateApiResponse(it) }
      .map { resource ->
        resource.data?.let { peopleResource ->
          Resource(SUCCESS, data = peopleResource.mapResponseToPeopleModel())
        } ?: Resource(ERROR, message = Error.GENERAL)
      }

  private fun providesQueriesToPeopleService(page: Int): Map<String, String> {
    val map = HashMap<String, String>()
    map[API_LANGUAGE] = "en"
    map[API_PAGE] = page.toString()
    return map
  }
}