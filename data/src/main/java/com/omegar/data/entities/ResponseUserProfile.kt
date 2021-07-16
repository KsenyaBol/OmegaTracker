package com.omegar.data.entities

import com.omegar.domain.entity.UserProfile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseUserProfile(
    @Json(name = "id")
    override val id: String,
    @Json(name = "email")
    override val email: String?,
    @Json(name = "login")
    override val login: String?,
    @Json(name = "name")
    override val name: String?
) : UserProfile