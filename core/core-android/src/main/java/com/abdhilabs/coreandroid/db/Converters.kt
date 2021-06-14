package com.abdhilabs.coreandroid.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromSource(genres: List<String>): String {
        return genres.joinToString(", ")
    }

    @TypeConverter
    fun toSource(genre: String): List<String> {
        return listOf(genre)
    }
//
//    data class Source(
//        val id: Any,
//        val name: String
//    )

//    private var genres: List<String>? = null
//
//    fun Converters(genres: List<String>?) {
//        this.genres = genres
//    }
//
//    fun getGenres(): List<String>? {
//        return genres
//    }
//
//    fun setGenres(genres: List<String>?) {
//        this.genres = genres
//    }

}