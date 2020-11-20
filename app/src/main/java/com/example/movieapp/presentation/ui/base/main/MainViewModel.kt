package com.example.movieapp.presentation.ui.base.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.model.People
import com.example.movieapp.data.remote.interceptor.NetworkInterceptor
import com.example.movieapp.presentation.common.Resource
import com.example.movieapp.presentation.common.ResourceState.SUCCESS
import com.example.movieapp.utility.extensions.addTo
import com.example.movieapp.utility.extensions.setError
import com.example.movieapp.utility.extensions.setLoading
import com.example.movieapp.utility.extensions.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 20 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  private val _people = MutableLiveData<Resource<People>>()

  val people: LiveData<Resource<People>>
    get() = _people

  var currentPage = 1

  fun loadLiveScores() {
    mainRepository.callPeopleService(currentPage)
      .doOnSubscribe { _people.setLoading() }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ peopleResource ->
        if (peopleResource.state == SUCCESS) {
          _people.setSuccess(peopleResource.data!!)
        } else {
          _people.setError(peopleResource.message)
        }
      }, { throwable ->
        if (throwable.message == NetworkInterceptor.NETWORK_ISSUE) {
          _people.setError(com.example.movieapp.utility.Error.NETWORK)
        } else {
          _people.setError(com.example.movieapp.utility.Error.GENERAL)
        }
      })
      .addTo(compositeDisposable)
  }

  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
  }
}