package com.omegar.omegatracker.di.modules

import com.omegar.data.api.TrackerApi
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
    fun providesLoginRepository(trackerApi: TrackerApi): LoginRepository =
        LoginRepositoryImpl(trackerApi)

    @Provides
    @Singleton
    fun providesIssueRepository(trackerApi: TrackerApi): IssueRepository =
        IssueRepositoryImpl(trackerApi)

}