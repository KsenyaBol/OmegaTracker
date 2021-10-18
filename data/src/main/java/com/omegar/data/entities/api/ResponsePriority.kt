package com.omegar.data.entities.api

import com.omegar.domain.entity.api.CustomFields
import com.omegar.domain.entity.api.Priority
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePriority(
    @Json(name = "name")
    override val name: String,
    @Json(name = "value")
    override val value: ResponsePriorityValue?
) : CustomFields, Priority