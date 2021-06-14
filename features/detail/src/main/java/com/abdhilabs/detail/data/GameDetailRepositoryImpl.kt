package com.abdhilabs.detail.data

import com.abdhilabs.coreandroid.data.dispatcher.CoroutineDispatcherProvider
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.source.GameRemoteDataSource
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.dao.GameFavoriteDao
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import com.abdhilabs.coreandroid.db.entity.toGameFavorite
import com.abdhilabs.detail.domain.GameDetailRepository
import javax.inject.Inject

class GameDetailRepositoryImpl @Inject constructor(
    private val remote: GameRemoteDataSource,
    private val dispatcher: CoroutineDispatcherProvider,
    private val favoriteDao: GameFavoriteDao,
    private val gameMapper: GameMapper
): GameDetailRepository {

    override suspend fun getGame(idGame: Int): Result<Game> {
        return when (val apiResult = remote.game(dispatcher.io, idGame)) {
            is Result.Success -> Result.Success(gameMapper.map(apiResult.data))
            is Result.Error -> Result.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
            else -> Result.Error()
        }
    }

    override suspend fun isFavorite(id: Int): List<GameFavorite> {
        return favoriteDao.isFavorite(id)
    }

    override suspend fun addToFavorite(game: Game) {
        favoriteDao.insertFavoriteGame(game.toGameFavorite())
    }

    override suspend fun deleteFromFavorite(idGame: Int) {
        favoriteDao.deleteFavouriteMovie(idGame)
    }
}