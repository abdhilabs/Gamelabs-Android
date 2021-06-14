package com.abdhilabs.detail.di

import com.abdhilabs.coreandroid.di.appDaggerComponent
import com.abdhilabs.detail.ui.DetailGameActivity

object DetailInjector {
    fun of(activity: DetailGameActivity) {
        DaggerDetailComponent.builder()
            .appComponent(activity.appDaggerComponent())
            .build()
            .inject(activity)
    }
}