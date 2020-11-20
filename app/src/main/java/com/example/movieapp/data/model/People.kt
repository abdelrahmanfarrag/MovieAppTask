package com.example.movieapp.data.model

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
data class People(
  val page: Int,
  val results: List<PeopleResult>,
  val totalPages: Int
) {

  data class PeopleResult(
    val id: Int,
    val name: String,
    val profilePath: String,
    val popularity: Double

  )
}