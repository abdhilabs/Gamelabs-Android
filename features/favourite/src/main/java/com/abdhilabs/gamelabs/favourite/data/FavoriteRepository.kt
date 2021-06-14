package com.abdhilabs.gamelabs.favourite.data

import com.abdhilabs.coreandroid.db.GameDatabase
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoriteRepository {
    suspend fun getAllFavoriteGame(): Flow<List<GameFavorite>>
}

class FavoriteRepositoryImpl @Inject constructor(
    private val db: GameDatabase
) : FavoriteRepository {
    override suspend fun getAllFavoriteGame(): Flow<List<GameFavorite>> {
        return db.favoriteDAO().getAllFavoriteGame()
    }
}