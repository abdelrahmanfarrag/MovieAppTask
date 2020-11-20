package com.example.movieapp.utility

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 */
 fun downloadFile(fileUrl: String, context: Context) {
  val request = DownloadManager.Request(Uri.parse(fileUrl))
    .setTitle(URLUtil.guessFileName(fileUrl, null, null))
    .setMimeType(getMimeType(fileUrl))
    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    .setDestinationInExternalPublicDir(
      Environment.DIRECTORY_DOWNLOADS, "/${URLUtil.guessFileName(fileUrl, null, null)}"
    )
    .setAllowedOverMetered(true)
    .setAllowedOverRoaming(true)
  val downloadManager =
    context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
  downloadManager.enqueue(request)
  Toast.makeText(context, context.resources.getString(R.string.loaded_success), Toast.LENGTH_LONG)
    .show()
}

private fun getRandomString(length: Int): String {
  val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
  return (1..length)
    .map { allowedChars.random() }
    .joinToString("")
}

fun getMimeType(url: String?): String? {
  var type: String? = null
  val extension: String? = MimeTypeMap.getFileExtensionFromUrl(url)
  if (extension != null) {
    type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
  }
  return type
}

