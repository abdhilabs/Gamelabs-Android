package com.abdhilabs.coreandroid.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdhilabs.coreandroid.data.vo.Constants.DELETE_FAVORITE_GAME_WITH_ID
import com.abdhilabs.coreandroid.data.vo.Constants.FILTER_FAVORITE_GAME_WITH_ID
import com.abdhilabs.coreandroid.data.vo.Constants.GET_ALL_FAVORITE_GAME
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface GameFavoriteDao {
    @Query(GET_ALL_FAVORITE_GAME)
    fun getAllFavoriteGame(): Flow<List<GameFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(entity: GameFavorite): Long

    @Query(FILTER_FAVORITE_GAME_WITH_ID)
    suspend fun isFavorite(id: Int): List<GameFavorite>

    @Query(DELETE_FAVORITE_GAME_WITH_ID)
    suspend fun deleteFavouriteMovie(id: Int)
}