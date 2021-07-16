package com.omegar.data.api

import com.omegar.data.entities.ResponseUserProfile
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TrackerApi {
    @GET("api/admin/users/me")
    suspend fun getUserData(
        @Header("Authorized") token: String,
        @Query("fields") fields: String
    ): ResponseUserProfile
}