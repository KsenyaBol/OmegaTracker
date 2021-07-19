package com.omegar.data.api

import com.omegar.data.entities.ResponseUserProfile
import retrofit2.http.*

interface TrackerApi {
    @GET("api/users/me")
    suspend fun getUserData(
        @Header("Authorization") token: String,
        @Query("fields") fields: String
    ): ResponseUserProfile
}