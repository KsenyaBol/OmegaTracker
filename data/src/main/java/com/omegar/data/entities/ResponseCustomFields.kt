package com.omegar.data.entities

import com.omegar.data.SingleToArray
import com.omegar.domain.entity.CustomFields
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCustomFields(
    @SingleToArray
    @Json(name = "value")
    override val value: List<ResponseCustomFieldsValue>?,

    @Json(name = "name")
    override val name: String
) : CustomFields