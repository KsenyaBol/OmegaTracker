package com.omegar.domain.repository

import com.omegar.domain.entity.UserProfile

interface LoginRepository {
    suspend fun getUserInfo(token: String): UserProfile
}