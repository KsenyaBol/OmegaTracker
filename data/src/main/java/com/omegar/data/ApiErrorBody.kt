package com.omegar.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiErrorBody(
    @Json(name = "error")
    val error: String,

    @Json(name = "error_description")
    val error_description: String
)