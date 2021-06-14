package com.abdhilabs.coreandroid.data.network.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ErrorResponse(
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "status")
    val status: String? = null
)