package com.omegar.domain.entity

import java.io.Serializable

interface CustomFields : Serializable {
    val value: List<CustomFieldsValue>?
    val name: String
}