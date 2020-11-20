package com.example.movieapp.data.remote.api

import com.example.movieapp.data.remote.api.Urls.EndPoints.PEOPLE
import com.example.movieapp.data.remote.api.Urls.EndPoints.PEOPLE_DETAIL
import com.example.movieapp.data.remote.response.PeopleDetailResponse
import com.example.movieapp.data.remote.response.PeopleResponse
import com.example.movieapp.utility.Constants.ARGS.KEY.API_KEY
import com.example.movieapp.utility.Constants.ARGS.VALUES.API_KEY_VALUE
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
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

  @GET(PEOPLE_DETAIL)
  fun loadPeopleDetails(
    @Path("person_id")  personId:Int,
    @Query(API_KEY) apiKey : String = API_KEY_VALUE
  ): Single<Response<PeopleDetailResponse>>



}