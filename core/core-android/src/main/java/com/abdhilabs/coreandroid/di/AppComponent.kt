package com.abdhilabs.coreandroid.di

import android.app.Application
import android.content.Context
import com.abdhilabs.coreandroid.di.module.CoroutineDispatcherModule
import com.abdhilabs.coreandroid.di.module.EncryptPreferenceModule
import com.abdhilabs.coreandroid.di.module.NetworkModule
import com.abdhilabs.coreandroid.di.module.RoomModule
import com.abdhilabs.gamelabs.di.module.ApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoroutineDispatcherModule::class,
        NetworkModule::class,
        ApiModule::class,
        EncryptPreferenceModule::class,
        RoomModule::class
    ]
)
interface AppComponent: AppDependencies {
    fun inject(instance: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun databaseModule(databaseModule: RoomModule): Builder

        fun build(): AppComponent
    }
}