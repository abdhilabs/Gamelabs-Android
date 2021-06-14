package com.abdhilabs.home.data

import com.abdhilabs.coreandroid.data.dispatcher.CoroutineDispatcherProvider
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.source.GameRemoteDataSource
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.dao.GameFavoriteDao
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import com.abdhilabs.coreandroid.db.entity.toGameFavorite
import com.abdhilabs.home.data.mapper.GameMapper
import com.abdhilabs.home.data.mapper.GamesMapper
import com.abdhilabs.home.domain.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remote: GameRemoteDataSource,
    private val dispatcher: CoroutineDispatcherProvider,
    private val favoriteDao: GameFavoriteDao,
    private val gamesMapper: GamesMapper,
    private val gameMapper: GameMapper
): GameRepository {

    override suspend fun getGames(): Result<List<Game>> {
        return when (val apiResult = remote.games(dispatcher.io)) {
            is Result.Success -> Result.Success(gamesMapper.map(apiResult.data))
            is Result.Error -> Result.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
            else -> Result.Error()
        }
    }

    override suspend fun getGame(idGame: Int): Result<Game> {
        return when (val apiResult = remote.game(dispatcher.io, idGame)) {
            is Result.Success -> Result.Success(gameMapper.map(apiResult.data))
            is Result.Error -> Result.Error(apiResult.cause, apiResult.code, apiResult.errorMessage)
            else -> Result.Error()
        }
    }

    override suspend fun searchGame(query: String): Result<List<Game>> {
        return when (val apiResult = remote.searchGames(dispatcher.io, query)) {
            is Result.Success -> Result.Success(gamesMapper.map(apiResult.data))
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