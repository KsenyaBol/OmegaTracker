package com.omegar.data.repository

import com.omegar.data.api.YouTrackApi
import com.omegar.data.entities.api.ResponseUserProfile
import com.omegar.domain.repository.LoginRepository

class LoginRepositoryImpl(private val youTrackApi: YouTrackApi) : LoginRepository {
    companion object {
        private const val REQUEST_FIELDS = "login,id,email,name"
    }

    override suspend fun getUserInfo(token: String): ResponseUserProfile {
        return youTrackApi.getUserData(token, REQUEST_FIELDS)
    }
}