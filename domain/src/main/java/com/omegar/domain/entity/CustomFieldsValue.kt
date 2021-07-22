package com.omegar.domain.entity

import java.io.Serializable

interface CustomFieldsValue : Serializable {
    val name: String?
    val presentation: String?
    val minutes: Int?
}