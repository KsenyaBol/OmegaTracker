package com.omegar.data.entities.api

import com.omegar.data.entities.enumcollection.ValueType
import com.omegar.domain.entity.api.Priority
import com.omegar.domain.entity.api.SpentTime
import com.omegar.domain.entity.api.State
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class ResponseValue(val valueType: ValueType) {
    @JsonClass(generateAdapter = true)
    data class ResponsePriority(
        @Json(name = "name")
        override val name: String,
        @Json(name = "value")
        override val value: ResponsePriorityValue?
    ) : ResponseValue(ValueType.PRIORITY), Priority

    @JsonClass(generateAdapter = true)
    data class ResponseState(
        @Json(name = "name")
        override val name: String,
        @Json(name = "value")
        override val value: ResponseStateValue?
    ) : ResponseValue(ValueType.STATE), State

    @JsonClass(generateAdapter = true)
    data class ResponseSpentTime(
        @Json(name = "name")
        override val name: String,
        @Json(name = "value")
        override val value: ResponseSpentTimeValue?
    ) : ResponseValue(ValueType.SPENT_TIME), SpentTime

    data class UnknownEntity(
        val name: String = "unknown"
    ) : ResponseValue(ValueType.UNKNOWN)
}