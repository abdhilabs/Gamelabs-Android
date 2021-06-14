package com.abdhilabs.gamelabs.favourite.di.module

import com.abdhilabs.gamelabs.favourite.data.FavoriteRepository
import com.abdhilabs.gamelabs.favourite.data.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FavoriteRepositoryModule {

    @Binds
    fun bindAuthRepository(repository: FavoriteRepositoryImpl): FavoriteRepository
}