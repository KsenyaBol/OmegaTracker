package com.omegar.data.entities.api

import com.omegar.domain.entity.api.StateValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseStateValue(
    @Json(name = "name")
    override val name: String
) : StateValue
