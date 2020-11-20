package com.example.movieapp.data.remote.api

import com.example.movieapp.data.remote.api.Urls.EndPoints.PEOPLE
import com.example.movieapp.data.remote.response.PeopleResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
interface Api {

  @GET(PEOPLE)
  fun loadPeople(
    @QueryMap queries: Map<String, String>
  ): Single<Response<PeopleResponse>>


}