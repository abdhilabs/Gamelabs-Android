package com.abdhilabs.home.data.mapper

import com.abdhilabs.coreandroid.abstraction.Mapper
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.network.response.GameResponse
import javax.inject.Inject

class GameMapper @Inject constructor() : Mapper<GameResponse.Result, Game>() {
    override fun map(input: GameResponse.Result): Game {
        return Game(
            input.id ?: 0,
            input.name ?: "",
            input.description ?: "",
            input.released ?: "0000-00-00",
            input.backgroundImage ?: "",
            input.rating ?: 0.0,
            mappingGenres(input.genres) ?: listOf()
        )
    }

    private fun mappingGenres(genres: List<GameResponse.Genres>?): List<String>? {
        return genres?.map { it.name ?: "" }
    }
}