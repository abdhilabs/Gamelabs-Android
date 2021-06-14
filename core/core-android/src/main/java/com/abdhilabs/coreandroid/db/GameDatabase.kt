package com.abdhilabs.coreandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abdhilabs.coreandroid.db.dao.GameFavoriteDao
import com.abdhilabs.coreandroid.db.entity.GameFavorite

@Database(entities = [GameFavorite::class], version = 1)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase() {
    abstract fun favoriteDAO(): GameFavoriteDao
}