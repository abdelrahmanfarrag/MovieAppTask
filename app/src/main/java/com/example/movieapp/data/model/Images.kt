package com.example.movieapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Parcelize
data class Images(
  val imagePath : List<String>
) : Parcelable