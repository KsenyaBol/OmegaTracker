package com.omegar.data.entities.api

import com.omegar.domain.entity.api.*
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
    override val customFields: List<CustomFields>,
) : Issue {
    override fun getPriority(): Priority? = customFields.filterIsInstance<Priority>().firstOrNull()
    override fun getState(): State? = customFields.filterIsInstance<State>().firstOrNull()
    override fun getSpentTime(): SpentTime? =
        customFields.filterIsInstance<SpentTime>().firstOrNull()
}
