package com.example.movieapp.data.remote.api

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
object Urls {
  const val BASE_URL = "https://api.themoviedb.org/3/"
  const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"


  object EndPoints {
    const val PEOPLE = "person/popular"
    const val PEOPLE_DETAIL = "person/{person_id}/images"
  }

}