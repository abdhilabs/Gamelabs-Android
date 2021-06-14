package com.abdhilabs.gamelabs.favourite.di.component

import com.abdhilabs.coreandroid.di.AppComponent
import com.abdhilabs.coreandroid.di.FeatureScope
import com.abdhilabs.gamelabs.favourite.di.module.FavoriteRepositoryModule
import com.abdhilabs.gamelabs.favourite.di.module.ViewModelModule
import com.abdhilabs.gamelabs.favourite.ui.FavoriteActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FavoriteRepositoryModule::class, ViewModelModule::class]
)
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)
}