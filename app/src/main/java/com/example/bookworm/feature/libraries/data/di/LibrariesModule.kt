package com.example.bookworm.feature.libraries.data.di

import android.app.Application
import com.example.bookworm.common.constants.BASE_URL
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.libraries.data.remote.LibrariesApiService
import com.example.bookworm.feature.libraries.data.remote.LibrariesDataSource
import com.example.bookworm.feature.libraries.data.repository.LibraryRepositoryImpl
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object LibrariesModule {

    @Provides
    @ViewModelScoped
    fun provideAPI(): LibrariesApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LibrariesApiService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideLibrariesDataSource(
        app: Application,
        api: LibrariesApiService,
        userPreferencesRepository: UserPreferencesRepository
    ): LibrariesDataSource {
        return LibrariesDataSource(
            context = app,
            librariesApi = api,
            userPreferencesRepository = userPreferencesRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideLibrariesRepo(librariesDataSource: LibrariesDataSource): LibraryRepository {
        return LibraryRepositoryImpl(
            librariesDataSource = librariesDataSource
        )
    }
}