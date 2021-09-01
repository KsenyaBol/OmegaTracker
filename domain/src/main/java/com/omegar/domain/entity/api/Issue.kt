package com.omegar.domain.entity.api

import java.io.Serializable

interface Issue : Serializable {
    val id: String
    val resolved: Long?
    val summary: String
    val customFields: List<Any>
}