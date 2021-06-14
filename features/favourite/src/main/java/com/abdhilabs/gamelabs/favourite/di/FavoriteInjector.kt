package com.abdhilabs.gamelabs.favourite.di

import com.abdhilabs.coreandroid.di.appDaggerComponent
import com.abdhilabs.gamelabs.favourite.di.component.DaggerFavoriteComponent
import com.abdhilabs.gamelabs.favourite.ui.FavoriteActivity

object FavoriteInjector {
    fun of(activity: FavoriteActivity) {
        DaggerFavoriteComponent.builder()
            .appComponent(activity.appDaggerComponent())
            .build()
            .inject(activity)
    }
}