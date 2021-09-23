package com.omegar.domain.entity.api

import java.io.Serializable

interface Issue : Serializable {
    val id: String
    val resolved: Long?
    val summary: String
    val customFields: List<CustomFields>
    fun getPriority(): Priority?
    fun getState(): State?
    fun getSpentTime(): SpentTime?
}