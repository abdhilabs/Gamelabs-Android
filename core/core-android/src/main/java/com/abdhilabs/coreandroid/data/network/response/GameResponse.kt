package com.abdhilabs.coreandroid.data.network.response

import com.squareup.moshi.Json

data class GameResponse(
    @Json(name = "count") val count: Int? = null,
    @Json(name = "results") val results: List<Result>? = null
) {
    data class Result(
        @Json(name = "id") val id: Int? = null,
        @Json(name = "name") val name: String? = null,
        @Json(name = "description_raw") val description: String? = null,
        @Json(name = "released") val released: String? = null,
        @Json(name = "background_image") val backgroundImage: String? = null,
        @Json(name = "rating") val rating: Double? = null,
        @Json(name = "genres") val genres: List<Genres>? = null
    )

    data class Genres(
        @Json(name = "id") val id: Int? = null,
        @Json(name = "name") val name: String? = null,
    )
}