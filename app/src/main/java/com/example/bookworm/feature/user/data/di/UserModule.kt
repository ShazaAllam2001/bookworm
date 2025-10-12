package com.example.bookworm.feature.user.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.bookworm.feature.user.data.local.UserPreferencesDataSource
import com.example.bookworm.feature.user.data.local.datastore
import com.example.bookworm.feature.user.data.repository.UserPreferencesRepositoryImpl
import com.example.bookworm.feature.user.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.user.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.user.data.remote.UserDataSource
import com.example.bookworm.feature.user.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.user.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserModule {

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.datastore
    }

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesSource(datastore: DataStore<UserPreferences>): UserPreferencesDataSource {
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
    fun provideUserDataSource(): UserDataSource {
        return UserDataSource()
    }

    @Provides
    @ViewModelScoped
    fun providesUserDataRepository(userDataSource: UserDataSource): UserDataRepository {
        return UserDataRepositoryImpl(userDataSource)
    }

}