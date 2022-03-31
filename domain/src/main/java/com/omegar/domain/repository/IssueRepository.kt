package com.omegar.domain.repository

import com.omegar.domain.entity.api.Issue

interface IssueRepository {

    suspend fun getIssuesForMe(token: String): List<Issue>
}
