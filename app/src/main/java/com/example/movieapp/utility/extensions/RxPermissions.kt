package com.example.movieapp.utility.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun RxPermissions.requestPermission(vararg permission: String): Observable<Boolean> = request(*permission)

fun Context.openPermSettings() =
  startActivity(
    Intent(
      Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
      Uri.parse("package:com.example.movieapp")
    )
  )
