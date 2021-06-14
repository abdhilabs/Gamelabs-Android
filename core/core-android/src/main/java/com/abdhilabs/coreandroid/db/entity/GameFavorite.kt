package com.abdhilabs.coreandroid.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Constants

@Entity(tableName = Constants.TABLE_NAME_GAME)
data class GameFavorite(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idGame")
    val id: Int = 0,
    val name: String? = null,
    val description: String? = null,
    val released: String? = null,
    val backgroundImage: String? = null,
    val rating: Double? = null,
    val genres: List<String>? = null
)

fun Game.toGameFavorite(): GameFavorite {
    return GameFavorite(id, name, description, released, backgroundImage, rating, genres)
}

fun GameFavorite.toGame(): Game {
    return Game(
        id,
        name ?: "",
        description ?: "",
        released ?: "",
        backgroundImage ?: "",
        rating ?: 0.0,
        genres ?: listOf()
    )
}
