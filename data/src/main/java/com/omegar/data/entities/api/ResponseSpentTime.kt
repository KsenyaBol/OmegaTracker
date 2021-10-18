package com.omegar.data.entities.api

import com.omegar.domain.entity.api.CustomFields
import com.omegar.domain.entity.api.SpentTime
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseSpentTime(
    @Json(name = "name")
    override val name: String,
    @Json(name = "value")
    override val value: ResponseSpentTimeValue?
) : CustomFields, SpentTime