package com.example.bookworm.common.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.bookworm.feature.auth.data.local.datastore
import com.example.bookworm.feature.auth.domain.model.preferences.UserPreferences
import com.example.bookworm.feature.settings.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.datastore
    }

    @Provides
    @ViewModelScoped
    fun bindUserDataRepository(): UserDataRepository {
        return UserDataRepositoryImpl()
    }
}