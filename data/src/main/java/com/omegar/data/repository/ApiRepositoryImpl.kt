package com.omegar.data.repository

import com.omegar.data.api.TrackerApi
import com.omegar.data.entities.ResponseIssues
import com.omegar.data.entities.ResponseUserProfile

class ApiRepositoryImpl(private val trackerApi: TrackerApi) : ApiRepository {

    override suspend fun getUserInfo(token: String, fields: String): ResponseUserProfile {
        return trackerApi.getUserData(token, fields)
    }

    override suspend fun getIssues(
        token: String,
        query: String,
        fields: String
    ): List<ResponseIssues> {
        return trackerApi.getIssues(token, query, fields)
    }

}