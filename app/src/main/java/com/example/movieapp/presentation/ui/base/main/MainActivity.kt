package com.example.movieapp.presentation.ui.base.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.movieapp.R.layout
import com.example.movieapp.di.presentation.activity.ActivitySubComponent
import com.example.movieapp.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.movieapp.presentation.Layout
import com.example.movieapp.presentation.common.ResourceState
import com.example.movieapp.presentation.ui.base.BaseActivity
import com.example.movieapp.utility.extensions.getViewModel
import com.example.movieapp.utility.extensions.toast
import javax.inject.Inject

@Layout(layout.activity_main)
class MainActivity : BaseActivity() {

  @Inject lateinit var factory: ViewModelFactoryProvider

  override fun setupInjection(component: ActivitySubComponent) {
    component.inject(this)
  }

  private val mainViewModel by lazy { getViewModel(MainViewModel::class.java, factory) }
  override fun afterViewsInstantiated(savedInstanceState: Bundle?) {
    callLoadPeopleService()
    observePeopleServiceResponse()
  }

  private fun callLoadPeopleService(currentPage: Int = 1) {
    mainViewModel.currentPage = currentPage
    mainViewModel.loadLiveScores()
  }

  private fun observePeopleServiceResponse() {
    mainViewModel.people.observe(this, Observer { peopleResource ->
      when (peopleResource.state) {
        ResourceState.LOADING -> toast("Showing loader")
        ResourceState.SUCCESS -> {
          peopleResource.data?.let { people ->
            toast("${people.results.size}")
          }
        }
        ResourceState.ERROR -> toast("hide loader")
      }
    })
  }
}

private fun whenAPersonIsClickedShowBottomSheetDialogToViewItsDetails(id: Int) {

}




