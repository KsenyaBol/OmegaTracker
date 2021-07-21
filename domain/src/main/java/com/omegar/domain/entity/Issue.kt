package com.omegar.domain.entity

interface Issue {
    val id: String
    val resolved: Int?
    val summary: String
    val customFields: CustomFields
}