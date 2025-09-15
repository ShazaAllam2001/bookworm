package com.example.bookworm.feature.auth.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.bookworm.common.data.mapper.UserMapper
import com.example.bookworm.feature.auth.data.local.UserPreferencesDataSource
import com.example.bookworm.feature.auth.data.local.datastore
import com.example.bookworm.feature.auth.data.remote.AuthDataSource
import com.example.bookworm.feature.auth.data.repository.AuthRepositoryImpl
import com.example.bookworm.feature.auth.data.repository.UserPreferencesRepositoryImpl
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.auth.domain.repository.AuthRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.datastore
    }

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesSource(datastore: DataStore<UserPreferences>): UserPreferencesDataSource{
        return UserPreferencesDataSource(datastore)
    }

    @Provides
    @ViewModelScoped
    fun providesUserPreferencesRepository(userPreferencesDataSource: UserPreferencesDataSource)
        : UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(userPreferencesDataSource)
    }

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