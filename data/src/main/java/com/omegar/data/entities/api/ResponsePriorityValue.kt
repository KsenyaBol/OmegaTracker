package com.omegar.data.entities.api

import com.omegar.domain.entity.api.PriorityValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePriorityValue(
    @Json(name = "name")
    override val name: String?
) : PriorityValue