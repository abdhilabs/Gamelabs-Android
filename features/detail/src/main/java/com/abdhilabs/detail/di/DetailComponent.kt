package com.abdhilabs.detail.di

import com.abdhilabs.coreandroid.di.AppComponent
import com.abdhilabs.coreandroid.di.FeatureScope
import com.abdhilabs.detail.ui.DetailGameActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DetailModule::class]
)
interface DetailComponent {
    fun inject(activity: DetailGameActivity)
}