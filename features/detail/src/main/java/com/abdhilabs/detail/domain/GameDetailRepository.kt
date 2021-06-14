package com.abdhilabs.detail.domain

import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.entity.GameFavorite

interface GameDetailRepository {
    suspend fun getGame(idGame: Int): Result<Game>
    suspend fun isFavorite(id: Int): List<GameFavorite>
    suspend fun addToFavorite(game: Game)
    suspend fun deleteFromFavorite(idGame: Int)
}