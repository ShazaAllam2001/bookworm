package com.example.bookworm.common.di

import android.app.Application
import com.example.bookworm.common.network.BASE_URL
import com.example.bookworm.common.network.BooksApiService
import com.example.bookworm.feature.auth.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.auth.data.repository.UserPreferencesRepositoryImpl
import com.example.bookworm.feature.auth.domain.repository.UserDataRepository
import com.example.bookworm.feature.auth.domain.repository.UserPreferencesRepository
import com.example.bookworm.feature.books.data.repository.BooksRepositoryImpl
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import com.example.bookworm.feature.libraries.data.repository.LibraryRepositoryImpl
import com.example.bookworm.feature.libraries.domain.repository.LibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideAPI(): BooksApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBooksRepo(api: BooksApiService): BooksRepository {
        return BooksRepositoryImpl(
            booksApi = api
        )
    }

    @Provides
    @Singleton
    fun provideLibrariesRepo(api: BooksApiService, userPreferencesRepository: UserPreferencesRepository): LibraryRepository {
        return LibraryRepositoryImpl(
            booksApi = api,
            userPreferencesRepository = userPreferencesRepository
        )
    }
}