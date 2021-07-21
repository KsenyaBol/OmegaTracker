package com.omegar.data.entities

import com.omegar.domain.entity.CustomFieldsValue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCustomFieldsValue(
    @Json(name = "name")
    override val name: String?,
    @Json(name = "presentation")
    override val presentation: String?,
    @Json(name = "minutes")
    override val minutes: Int?
) : CustomFieldsValue