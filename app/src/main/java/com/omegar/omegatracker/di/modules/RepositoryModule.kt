package com.omegar.omegatracker.di.modules

import com.omegar.data.api.YouTrackApi
import com.omegar.data.repository.IssueRepositoryImpl
import com.omegar.data.repository.LoginRepositoryImpl
import com.omegar.domain.repository.IssueRepository
import com.omegar.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesLoginRepository(youTrackApi: YouTrackApi): LoginRepository =
        LoginRepositoryImpl(youTrackApi)

    @Provides
    @Singleton
    fun providesIssueRepository(youTrackApi: YouTrackApi): IssueRepository =
        IssueRepositoryImpl(youTrackApi)

}