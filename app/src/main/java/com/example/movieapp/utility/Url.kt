package com.example.movieapp.utility

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.wajahatkarim3.easyvalidation.core.view_ktx.validUrl

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */
fun Context.openUrl(url: String) {
  if (!url.validUrl()) return
  var newUrl = url
  if (!url.startsWith("http://") && !url.startsWith("https://")) newUrl = "http://$url"
  val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newUrl))
  browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  startActivity(browserIntent)
}