package com.example.bookworm.feature.auth.data.di

import com.example.bookworm.feature.auth.data.repository.AuthRepositoryImpl
import com.example.bookworm.feature.auth.data.repository.UserPreferencesRepositoryImpl
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.settings.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindUserPreferencesRepository(
        impl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository

}