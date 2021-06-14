package com.abdhilabs.coreandroid.di

import com.abdhilabs.coreandroid.data.network.service.GameService
import com.abdhilabs.coreandroid.db.GameDatabase
import com.abdhilabs.coreandroid.db.dao.GameFavoriteDao

interface AppDependencies {
    fun gameService(): GameService
    fun favoriteDaoService(): GameFavoriteDao
    fun dbService(): GameDatabase
}