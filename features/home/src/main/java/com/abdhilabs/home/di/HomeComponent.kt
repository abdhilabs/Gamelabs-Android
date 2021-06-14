package com.abdhilabs.home.di

import com.abdhilabs.coreandroid.di.AppComponent
import com.abdhilabs.coreandroid.di.FeatureScope
import com.abdhilabs.home.ui.HomeActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [HomeModule::class]
)
interface HomeComponent {
    fun inject(activity: HomeActivity)
}