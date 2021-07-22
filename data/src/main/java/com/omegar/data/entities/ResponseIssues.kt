package com.omegar.data.entities

import com.omegar.domain.entity.Issue
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseIssues(
    @Json(name = "id")
    override val id: String,

    @Json(name = "resolved")
    override val resolved: Long?,

    @Json(name = "summary")
    override val summary: String,

    @Json(name = "customFields")
    override val customFields: List<ResponseCustomFields>?,
) : Issue