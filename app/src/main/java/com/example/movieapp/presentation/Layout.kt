package com.example.movieapp.presentation

import androidx.annotation.LayoutRes
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Retention(RUNTIME)
@Target(CLASS)
annotation class Layout(@LayoutRes val value: Int)