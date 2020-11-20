package com.example.movieapp.presentation.common

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
sealed class ResourceState {

  object LOADING : ResourceState()
  object SUCCESS : ResourceState()
  object ERROR : ResourceState()
}