package com.abdhilabs.coreandroid.data.vo

object Constants {
    const val TABLE_NAME_GAME = "tb_game_favorite"
    const val GET_ALL_FAVORITE_GAME = "SELECT * FROM $TABLE_NAME_GAME"
    const val FILTER_FAVORITE_GAME_WITH_ID = "SELECT * FROM $TABLE_NAME_GAME WHERE idGame=:id"
    const val DELETE_FAVORITE_GAME_WITH_ID = "DELETE FROM $TABLE_NAME_GAME WHERE idGame=:id"
    const val FAVORITE_DATABASE_NAME = "gamelabs.db"
}