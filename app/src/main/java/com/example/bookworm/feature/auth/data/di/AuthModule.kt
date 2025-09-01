package com.example.bookworm.feature.auth.data.di

import android.app.Application
import com.example.bookworm.feature.auth.data.repository.UserPreferencesRepositoryImpl
import com.example.bookworm.feature.auth.data.mapper.AuthMapper
import com.example.bookworm.feature.auth.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.auth.data.repository.AuthRepositoryImpl
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserDataRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthMapper(): AuthMapper {
        return AuthMapper()
    }

    @Provides
    @Singleton
    fun provideAuthRepo(authMapper: AuthMapper): AuthRepository {
        return AuthRepositoryImpl(
            authMapper = authMapper
        )
    }

    @Provides
    @Singleton
    fun provideUserPrefRepo(app: Application): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(
            context = app
        )
    }

    @Provides
    @Singleton
    fun provideUserDataRepo(): UserDataRepository {
        return UserDataRepositoryImpl()
    }
}