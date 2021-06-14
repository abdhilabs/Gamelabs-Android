package com.abdhilabs.coreandroid.di.module

import android.content.Context
import androidx.room.Room
import com.abdhilabs.coreandroid.data.vo.Constants.FAVORITE_DATABASE_NAME
import com.abdhilabs.coreandroid.db.GameDatabase
import com.abdhilabs.coreandroid.db.dao.GameFavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(applicationContext: Context): GameDatabase {
        return Room.databaseBuilder(applicationContext, GameDatabase::class.java, FAVORITE_DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(db: GameDatabase): GameFavoriteDao {
        return db.favoriteDAO()
    }
}
