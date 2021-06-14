package com.abdhilabs.coreandroid.data.network.service

import com.abdhilabs.coreandroid.BuildConfig
import com.abdhilabs.coreandroid.data.network.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameService {

    @GET("games")
    suspend fun games(@Query("key") apiKey: String = BuildConfig.API_KEY): GameResponse

    @GET("games/{id}")
    suspend fun game(
        @Path("id") idGame: Int,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): GameResponse.Result

    @GET("games")
    suspend fun searchGame(
        @Query("search") query: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): GameResponse
}