package com.omegar.data.api

import com.omegar.data.entities.ResponseIssues
import com.omegar.data.entities.ResponseUserProfile
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TrackerApi {
    @GET("api/users/me")
    suspend fun getUserData(
        @Header("Authorization") token: String,
        @Query("fields") fields: String
    ): ResponseUserProfile

    @GET("api/issues?query=for:me&fields=id,summary,resolved,customFields(name,value(minutes,name,presentation))")
    suspend fun getIssues(
        @Header("Authorization") token: String,
        @Query("query") query: String,
        @Query("fields") fields: String
    ): List<ResponseIssues>
}