package com.abdhilabs.coreandroid.di.module

import com.abdhilabs.coreandroid.data.dispatcher.CoroutineDispatcherProvider
import com.abdhilabs.coreandroid.data.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module

@Module
interface CoroutineDispatcherModule {

    @Binds
    fun bindDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}