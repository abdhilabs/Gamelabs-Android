package com.abdhilabs.gamelabs.di.module

import com.abdhilabs.coreandroid.data.network.service.GameService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }
}