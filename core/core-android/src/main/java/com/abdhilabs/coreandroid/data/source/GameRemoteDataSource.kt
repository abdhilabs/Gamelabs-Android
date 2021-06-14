package com.abdhilabs.coreandroid.data.source

import com.abdhilabs.coreandroid.data.network.response.GameResponse
import com.abdhilabs.coreandroid.data.network.service.GameService
import com.abdhilabs.coreandroid.data.vo.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GameRemoteDataSource @Inject constructor(private val service: GameService) :
    RemoteDataSource() {

    suspend fun games(dispatcher: CoroutineDispatcher): Result<GameResponse> {
        return safeApiCall(dispatcher) { service.games() }
    }

    suspend fun game(dispatcher: CoroutineDispatcher, idGame: Int): Result<GameResponse.Result> {
        return safeApiCall(dispatcher) { service.game(idGame) }
    }

    suspend fun searchGames(dispatcher: CoroutineDispatcher, query: String): Result<GameResponse> {
        return safeApiCall(dispatcher) { service.searchGame(query) }
    }
}