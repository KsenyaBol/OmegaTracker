package com.omegar.data.sources

import com.omega_r.base.data.sources.OmegaRemoteSource
import com.omegar.data.api.YouTrackApi
import com.omegar.data.entities.api.ResponseUserProfile
import com.omegar.data.repository.DataLoginSource

class RemoteLoginSource(private val youTrackApi: YouTrackApi): OmegaRemoteSource(), DataLoginSource {
    companion object {
        private const val REQUEST_FIELDS = "login,id,email,name"
    }

    override suspend fun getUserInfo(token: String): ResponseUserProfile {
        return youTrackApi.getUserData(token, REQUEST_FIELDS)
    }

}