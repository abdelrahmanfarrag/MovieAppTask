package com.example.movieapp.di.presentation.activity

import android.content.Context
import com.example.movieapp.di.presentation.scopes.PerActivity
import com.example.movieapp.presentation.ui.base.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class, ActivityViewModelModule::class])
interface ActivitySubComponent {

  fun inject(mainActivity: MainActivity)

  @Subcomponent.Builder
  interface Builder {

    @BindsInstance fun bindsContext(context: Context): Builder

    fun build(): ActivitySubComponent
  }
}