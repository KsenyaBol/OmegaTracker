package com.omegar.domain.entity

import java.io.Serializable

interface UserProfile : Serializable {
    val id: String
    val email: String?
    val login: String?
    val name: String?
}