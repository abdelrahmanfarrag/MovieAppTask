package com.example.movieapp.presentation.common

import com.example.movieapp.utility.Error

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
class Resource<out T> constructor(
  val state: ResourceState,
  val data: T? = null,
  val message: String = Error.GENERAL
)