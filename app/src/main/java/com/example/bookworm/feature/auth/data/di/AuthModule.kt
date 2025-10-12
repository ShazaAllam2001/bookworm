package com.example.bookworm.feature.auth.data.di

import com.example.bookworm.feature.user.data.mapper.UserMapper
import com.example.bookworm.feature.auth.data.remote.AuthDataSource
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
    fun provideAuthDataSource(userMapper: UserMapper): AuthDataSource {
        return AuthDataSource(userMapper)
    }

    @Provides
    @ViewModelScoped
    fun providesAuthRepository(authDataSource: AuthDataSource): AuthRepository {
        return AuthRepositoryImpl(authDataSource)
    }

}