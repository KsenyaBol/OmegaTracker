package com.omegar.omegatracker.di

import com.omegar.domain.repository.IssueRepository
import com.omegar.domain.repository.LoginRepository
import com.omegar.domain.storage.TokenStorage
import com.omegar.omegatracker.di.modules.ContextModule
import com.omegar.omegatracker.di.modules.RepositoryModule
import com.omegar.omegatracker.di.modules.StorageModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RepositoryModule::class,
        StorageModule::class
    ]
)
interface AppComponent {

    val loginRepository: LoginRepository
    val issueRepository: IssueRepository
    val tokenStorage: TokenStorage
}