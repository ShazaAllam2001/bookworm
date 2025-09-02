package com.example.bookworm.feature.auth.data.di

import com.example.bookworm.feature.auth.data.mapper.AuthMapper
import com.example.bookworm.feature.auth.data.repository.AuthRepositoryImpl
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthMapper(): AuthMapper {
        return AuthMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepo(authMapper: AuthMapper): AuthRepository {
        return AuthRepositoryImpl(
            authMapper = authMapper
        )
    }
}