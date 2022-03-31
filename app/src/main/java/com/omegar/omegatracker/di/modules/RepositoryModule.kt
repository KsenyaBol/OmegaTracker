package com.omegar.omegatracker.di.modules

import com.omega_r.base.errors.ErrorHandler
import com.omegar.data.AppErrorHandler
import com.omegar.data.api.YouTrackApi
import com.omegar.data.repository.DataIssueOmegaRepository
import com.omegar.data.repository.DataIssueSource
import com.omegar.data.repository.DataLoginOmegaRepository
import com.omegar.data.repository.DataLoginSource
import com.omegar.domain.repository.IssueRepository
import com.omegar.domain.repository.LoginRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    RemoteSourceModule::class
])
object RepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(sources: Set<@JvmSuppressWildcards DataLoginSource>, handler: ErrorHandler): LoginRepository =
        DataLoginOmegaRepository(handler, *sources.toTypedArray())

    @Provides
    @Singleton
    fun providesIssueRepository(sources: Set<@JvmSuppressWildcards DataIssueSource>, handler: ErrorHandler): IssueRepository =
        DataIssueOmegaRepository(handler, *sources.toTypedArray())

    @Provides
    @Singleton
    fun provideErrorHandler(moshi: Moshi): ErrorHandler = AppErrorHandler(moshi)
}