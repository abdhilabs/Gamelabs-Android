package com.abdhilabs.home.domain

import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.entity.GameFavorite

interface GameRepository {
    suspend fun getGames(): Result<List<Game>>
    suspend fun getGame(idGame: Int): Result<Game>
    suspend fun searchGame(query: String): Result<List<Game>>
    suspend fun isFavorite(id: Int): List<GameFavorite>
    suspend fun addToFavorite(game: Game)
    suspend fun deleteFromFavorite(idGame: Int)
}