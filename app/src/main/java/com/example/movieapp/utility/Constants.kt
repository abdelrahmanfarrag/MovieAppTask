package com.example.movieapp.utility

import android.Manifest.permission

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class Constants {

  object PREFS {

    object LANGUAGE {
      const val NAME = "SETTINGS"

      object KEY {
        const val LANGUAGE = "Language"
      }
    }

  }

  object PERMISSIONS {
    const val CAMERA = permission.CAMERA
    const val GALLERY = permission.READ_EXTERNAL_STORAGE
    const val FINE_LOCATION = permission.ACCESS_FINE_LOCATION
    const val COARSE_LOCATION = permission.ACCESS_COARSE_LOCATION
  }

  object ARGS {

    object KEY {
      const val API_KEY = "api_key"
      const val API_LANGUAGE = "language"
      const val API_PAGE = "page"
    }

    object VALUES {
      const val API_KEY_VALUE = "f696be1437ab553903ebf8ff5203da2e"

    }
  }

  object REQUEST {}
}