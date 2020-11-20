package com.example.movieapp.di.application.component

import android.app.Application
import com.example.movieapp.di.application.module.ApiModule
import com.example.movieapp.di.application.scope.ApplicationScope
import com.example.movieapp.di.presentation.activity.ActivitySubComponent
import dagger.BindsInstance
import dagger.Component

/**
 * Authored by Abdelrahman Ahmed on 19 Nov, 2020.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@ApplicationScope
@Component(modules = [ApiModule::class])
interface AppComponent {

  fun getActivitySubComponent(): ActivitySubComponent.Builder

  @Component.Builder
  interface Builder {

    @BindsInstance fun bindApplication(application: Application): Builder
    fun build(): AppComponent

  }
}