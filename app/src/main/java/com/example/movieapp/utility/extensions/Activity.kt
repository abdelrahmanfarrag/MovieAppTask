package com.example.movieapp.utility.extensions

import com.example.movieapp.presentation.ui.base.BaseActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */


fun BaseActivity.startActivity(cls: Class<*>, extras: Bundle? = null) {
  val intent = android.content.Intent(this, cls)
  extras?.let { intent.putExtras(it) }
  startActivity(intent/*, ActivityOptions.makeSceneTransitionAnimation(this).toBundle()*/)
}

fun BaseActivity.startActivityNewTask(cls: Class<*>, extras: Bundle? = null) {
  val intent = android.content.Intent(this, cls)
  intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
  extras?.let { intent.putExtras(it) }
  startActivity(intent/*, ActivityOptions.makeSceneTransitionAnimation(this).toBundle()*/)
}

fun BaseActivity.hideKeypad() {
  currentFocus?.let {
    val imm = getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(it.windowToken, 0)
  }
}

fun BaseActivity.showFragment(
  fragment: Fragment,
  @IdRes container: Int,
  replace: Boolean,
  isAnimated: Boolean = false,
  addReplacedToBAckStack: Boolean = false,
  tag: String = ""
) {
  supportFragmentManager.apply {
    beginTransaction().apply {
      setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
      if (isAnimated) {
       // this.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up)
      }
      if (replace) {
        replace(container, fragment).commit()
        if (addReplacedToBAckStack) addToBackStack(null)
      } else {
        add(container, fragment, tag).commit()
        addToBackStack(null)
        executePendingTransactions()
      }
    }
  }
}

fun BaseActivity.clearBackStack() {
  supportFragmentManager.let {
    it.apply { if (backStackEntryCount > 0) for (i in 0..backStackEntryCount) popBackStack() }
  }
}

fun <T : ViewModel> BaseActivity.getViewModel(
  modelClass: Class<T>,
  viewModelFactory: ViewModelProvider.Factory? = null
): T {
  return viewModelFactory?.let {
    ViewModelProvider(this, it).get(modelClass)
  } ?: androidx.lifecycle.ViewModelProvider(this).get(modelClass)
}