package com.abdhilabs.gamelabs

import android.app.Application
import com.abdhilabs.coreandroid.di.AppComponent
import com.abdhilabs.coreandroid.di.DaggerAppComponent
import com.abdhilabs.coreandroid.di.DependencyProvider
import com.abdhilabs.coreandroid.di.module.NetworkModule
import com.abdhilabs.coreandroid.di.module.RoomModule
import timber.log.Timber

open class BaseApp : Application(), DependencyProvider {

    override lateinit var appComponent: AppComponent
        protected set

    override fun onCreate() {
        super.onCreate()
        appComponent = initAppComponent()
        appComponent.inject(this)
        initTimber()
    }

    override fun provideDeps(): AppComponent = initAppComponent()

    private fun initAppComponent() = DaggerAppComponent.builder()
        .application(this)
        .context(this.applicationContext)
        .networkModule(NetworkModule())
        .databaseModule(RoomModule())
        .build()


    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}