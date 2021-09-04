package com.omegar.data.repository

import com.omegar.data.api.TrackerApi
import com.omegar.data.entities.api.ResponseUserProfile
import com.omegar.domain.repository.LoginRepository

class LoginRepositoryImpl(private val trackerApi: TrackerApi) : LoginRepository {
    companion object {
        private const val REQUEST_FIELDS = "login,id,email,name"
    }

    override suspend fun getUserInfo(token: String): ResponseUserProfile {
        return trackerApi.getUserData(token, REQUEST_FIELDS)
    }
}