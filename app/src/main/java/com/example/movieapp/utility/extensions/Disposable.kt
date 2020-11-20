package com.example.movieapp.utility.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)