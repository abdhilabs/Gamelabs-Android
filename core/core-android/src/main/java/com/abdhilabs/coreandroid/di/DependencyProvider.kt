package com.abdhilabs.coreandroid.di

interface DependencyProvider {
    val appComponent: AppComponent
        get() = provideDeps()

    fun provideDeps(): AppComponent
}