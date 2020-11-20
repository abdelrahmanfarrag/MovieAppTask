package com.example.movieapp.utility.extensions

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings.Secure
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.movieapp.R

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 */

fun Context.toast(msg: String?) {
  msg?.let {
    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
  }
}

fun Context.toastLong(msg: String?) {
  msg?.let {
    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
  }
}

@Suppress("DEPRECATION") fun Context.isNetworkAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
      else -> false
    }
  } else {
    val nwInfo = connectivityManager.activeNetworkInfo ?: return false
    return nwInfo.isConnected
  }
}

fun Context.getAppDrawable(
  @DrawableRes idRes: Int
): Drawable? = ContextCompat.getDrawable(this, idRes)

fun Context.getAppColor(
  @ColorRes idRes: Int
): Int = ContextCompat.getColor(this, idRes)

fun Context.getAccentColor(): Int {
  val typedValue = TypedValue()
  val array = obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
  val color = array.getColor(0, 0)
  array.recycle()
  return color
}

@SuppressLint("HardwareIds")
fun Context.getDeviceId(): String = Secure.getString(contentResolver, Secure.ANDROID_ID)

fun Context.isAppRunning(): Boolean {
  val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
  val processesInfo: MutableList<RunningAppProcessInfo>? = activityManager.runningAppProcesses
  processesInfo?.let { infoList ->
    if (infoList.isNotEmpty()) {
      infoList.forEach { processInfo ->
        if (processInfo.processName == packageName) return true
      }
    }
  }
  return false
}

@Suppress("DEPRECATION") fun Context.isActivityRunning(activityClass: Class<*>): Boolean {
  val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
  val tasks = activityManager.getRunningTasks(Int.MAX_VALUE)
  tasks.forEach { task ->
    if (activityClass.canonicalName.equals(task.baseActivity?.className, ignoreCase = true)) {
      return true
    }
  }
  return false
}