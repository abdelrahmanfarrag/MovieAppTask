package com.example.movieapp.utility.extensions

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.presentation.common.Resource
import com.example.movieapp.presentation.common.ResourceState.ERROR
import com.example.movieapp.presentation.common.ResourceState.LOADING
import com.example.movieapp.presentation.common.ResourceState.SUCCESS

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(
  data: T
) = postValue(Resource(SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading(
) = postValue(Resource(LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(
  message: String
) = postValue(Resource(ERROR, value?.data, message))
