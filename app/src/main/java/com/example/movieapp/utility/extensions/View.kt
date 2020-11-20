package com.example.movieapp.utility.extensions

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun View.visible() {
  visibility = View.VISIBLE
}

fun View.invisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun ViewGroup.inflate(
  layoutId: Int,
  attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadWithoutPlaceHolder(url: String) {
  Glide.with(context.applicationContext)
    .load(url)
    .into(this)
}

fun  RecyclerView.endlessScrolling(
  layoutManager: LinearLayoutManager,
  loadMore: (Int) -> Unit,
  page:Int
 // items: List<T>
) {
  this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
      if (dy > 0) {
        val visibleItemCount = layoutManager.childCount
        val totalItemsCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if (visibleItemCount + pastVisibleItems >= totalItemsCount) {
          loadMore(page)
        }
      }
    }
  })
}
fun RecyclerView.smoothSnapToPosition(position: Int, snapMode: Int = LinearSmoothScroller.SNAP_TO_START) {
  val smoothScroller = object : LinearSmoothScroller(this.context) {
    override fun getVerticalSnapPreference(): Int = snapMode
    override fun getHorizontalSnapPreference(): Int = snapMode
  }
  smoothScroller.targetPosition = position
  layoutManager?.startSmoothScroll(smoothScroller)
}
fun Snackbar.snackBarSettings(
  context: Context, message: String, colorRes: Int,
  duration: Int = BaseTransientBottomBar.LENGTH_INDEFINITE
): Snackbar {
  val view = this.view
  val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
  textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
  this.duration = duration
  val params = view.layoutParams as FrameLayout.LayoutParams
  params.gravity = Gravity.TOP
  this.view.layoutParams = params
  this.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
  textView.gravity = Gravity.CENTER_HORIZONTAL
  this.setBackgroundTint(ContextCompat.getColor(context, colorRes))
  return this
}

