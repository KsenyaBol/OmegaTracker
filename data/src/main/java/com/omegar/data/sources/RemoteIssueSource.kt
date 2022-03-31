package com.omegar.data.sources

import com.omega_r.base.data.sources.OmegaRemoteSource
import com.omegar.data.api.YouTrackApi
import com.omegar.data.repository.DataIssueSource
import com.omegar.domain.entity.api.Issue

class RemoteIssueSource(private val youTrackApi: YouTrackApi) : OmegaRemoteSource(), DataIssueSource {

    companion object {

        private const val REQUEST_QUERY = "for:me"
        private const val REQUEST_FIELDS =
            "id,summary,resolved,customFields(name,value(minutes,name,presentation))"
    }

    override suspend fun getIssuesForMe(token: String): List<Issue> {
        return youTrackApi.getIssues(token, REQUEST_QUERY, REQUEST_FIELDS)
    }
}