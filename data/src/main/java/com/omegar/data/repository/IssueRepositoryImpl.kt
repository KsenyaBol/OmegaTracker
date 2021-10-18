package com.omegar.data.repository

import com.omegar.data.api.YouTrackApi
import com.omegar.domain.entity.api.Issue
import com.omegar.domain.repository.IssueRepository

class IssueRepositoryImpl(private val youTrackApi: YouTrackApi) : IssueRepository {
    companion object {
        private const val REQUEST_QUERY = "for:me"
        private const val REQUEST_FIELDS =
            "id,summary,resolved,customFields(name,value(minutes,name,presentation))"
    }

    override suspend fun getIssuesForMe(token: String): List<Issue> {
        return youTrackApi.getIssues(token, REQUEST_QUERY, REQUEST_FIELDS)
    }
}