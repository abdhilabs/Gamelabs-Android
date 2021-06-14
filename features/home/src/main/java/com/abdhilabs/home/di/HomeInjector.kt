package com.abdhilabs.home.di

import com.abdhilabs.coreandroid.di.appDaggerComponent
import com.abdhilabs.home.ui.HomeActivity

object HomeInjector {
    fun of(activity: HomeActivity) {
        DaggerHomeComponent.builder()
            .appComponent(activity.appDaggerComponent())
            .build()
            .inject(activity)
    }
}