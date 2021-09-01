package com.omegar.data.entities.api

import com.omegar.domain.entity.api.SpentTimeValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseSpentTimeValue(
    @Json(name = "presentation")
    override val presentation: String?,
    @Json(name = "minutes")
    override val minutes: Long?
) : SpentTimeValue
