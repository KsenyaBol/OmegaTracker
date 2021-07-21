package com.omegar.data.entities

import com.omegar.domain.entity.CustomFields
import com.omegar.domain.entity.CustomFieldsValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCustomFields(
    @Json(name = "value")
    override val value: List<CustomFieldsValue>?,
    @Json(name = "name")
    override val name: String
) : CustomFields