package com.abdhilabs.home.data.mapper

import com.abdhilabs.coreandroid.abstraction.Mapper
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.network.response.GameResponse
import javax.inject.Inject

class GamesMapper @Inject constructor() : Mapper<GameResponse, List<Game>>() {
    override fun map(input: GameResponse): List<Game> {
        return input.results?.map {
            Game(
                it.id ?: 0,
                it.name ?: "",
                it.description ?: "",
                it.released ?: "0000-00-00",
                it.backgroundImage ?: "",
                it.rating ?: 0.0,
                mappingGenres(it.genres) ?: listOf()
            )
        } ?: emptyList()
    }

    private fun mappingGenres(genres: List<GameResponse.Genres>?): List<String>? {
        return genres?.map { it.name ?: "" }
    }
}