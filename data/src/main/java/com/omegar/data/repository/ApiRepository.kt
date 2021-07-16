package com.omegar.data.repository

import com.omega_r.base.annotations.AppOmegaRepository
import com.omegar.data.entities.ResponseUserProfile

@AppOmegaRepository
interface ApiRepository {
    suspend fun getUserInfo(token: String, fields: String): ResponseUserProfile
}