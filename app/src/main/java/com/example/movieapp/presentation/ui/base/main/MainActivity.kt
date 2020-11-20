package com.example.movieapp.presentation.ui.base.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.di.presentation.activity.ActivitySubComponent
import com.example.movieapp.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.movieapp.presentation.Layout
import com.example.movieapp.presentation.common.ResourceState
import com.example.movieapp.presentation.ui.base.BaseActivity
import com.example.movieapp.presentation.ui.base.main.adapter.PeopleAdapter
import com.example.movieapp.utility.extensions.endlessScrolling
import com.example.movieapp.utility.extensions.getViewModel
import com.example.movieapp.utility.extensions.snackBarSettings
import com.example.movieapp.utility.extensions.toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

@Layout(R.layout.activity_main)
class MainActivity : BaseActivity() {

  @Inject lateinit var factory: ViewModelFactoryProvider
  private lateinit var peopleRecyclerViewXX: RecyclerView
  private lateinit var adapter: PeopleAdapter
  private var lastIndexItem = 0
  private var currentPage = 1
  private var isFirstTimeLoading = true
  private var isLoading = false

  override fun setupInjection(component: ActivitySubComponent) {
    component.inject(this)
  }

  private val mainViewModel by lazy { getViewModel(MainViewModel::class.java, factory) }
  override fun afterViewsInstantiated(savedInstanceState: Bundle?) {
    peopleRecyclerViewXX = findViewById(R.id.peopleRecyclerview)
    adapter = PeopleAdapter()
    callLoadPeopleService()
    observePeopleServiceResponse()
    observePeopleDetailsServiceResponse()
  }

  private fun callLoadPeopleService(currentPage: Int = 1) {
    mainViewModel.currentPage = currentPage
    mainViewModel.loadPeople()
  }

  private fun addMoreItems(page: Int) {
    if (!isLoading) {
      callLoadPeopleService(page)
      isLoading = true
    }
  }

  private fun observePeopleServiceResponse() {
    mainViewModel.people.observe(this, Observer { peopleResource ->
      when (peopleResource.state) {
        ResourceState.LOADING -> createSnackBar(false)
        ResourceState.SUCCESS -> {
          peopleResource.data?.let { people ->
            createSnackBar(true)
            isLoading = false
            currentPage = people.page
            val layoutManager = GridLayoutManager(this, 2)
            if (people.results.isNotEmpty()) {
              adapter.setOnPeopleClickListener { peopleId ->
                whenAPersonIsClickedShowBottomSheetDialogToViewItsDetails(peopleId)
              }
              adapter.addItems(people.results)
              peopleRecyclerViewXX.layoutManager = layoutManager
              if (isFirstTimeLoading) {
                peopleRecyclerViewXX.adapter = adapter
              } else {
                lastIndexItem = adapter.itemCount - people.results.size
                layoutManager.scrollToPosition(lastIndexItem)

                adapter.notifyDataSetChanged()
              }
              peopleRecyclerViewXX.endlessScrolling(
                layoutManager,
                { addMoreItems(currentPage) },
                currentPage++
              )
            }
            isFirstTimeLoading = false
          }
        }
        ResourceState.ERROR -> createSnackBar(true)
      }
    })
  }

  private fun observePeopleDetailsServiceResponse() {
    mainViewModel.images.observe(this, Observer { peopleResource ->
      when (peopleResource.state) {
        ResourceState.LOADING -> createSnackBar(false)
        ResourceState.SUCCESS -> {
          peopleResource.data?.let { images ->
            createSnackBar(true)
            val detailSheetDialog = PeopleDetailBottomSheetDialog.newInstance(images)
            detailSheetDialog.show(supportFragmentManager, "People detail")


          }
        }
        ResourceState.ERROR -> createSnackBar(true)
      }
    })
  }

  private fun whenAPersonIsClickedShowBottomSheetDialogToViewItsDetails(id: Int) {
    mainViewModel.peopleId = id
    mainViewModel.loadPeopleDetails()
//    toast("$id")
//    val detailSheetDialog = PeopleDetailBottomSheetDialog.newInstance()
//    detailSheetDialog.show(supportFragmentManager, "People detail")
  }

  private fun createSnackBar(isLoading: Boolean) {
    val snackBar: Snackbar
    if (!isLoading) {
      snackBar = Snackbar.make(
        this.window.decorView.rootView,
        getString(R.string.loading),
        Snackbar.LENGTH_LONG
      ).snackBarSettings(
        this, "",
        R.color.md_red_400
      )
      snackBar.show()
    } else {

      snackBar = Snackbar.make(
        this.window.decorView.rootView,
        getString(R.string.loaded_success),
        Snackbar.LENGTH_LONG
      ).snackBarSettings(
        this, "", R.color.md_green_300,
        duration = BaseTransientBottomBar.LENGTH_SHORT
      )
      snackBar.show()
    }
  }
}






