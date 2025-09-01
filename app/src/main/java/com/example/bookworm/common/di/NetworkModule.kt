package com.example.bookworm.common.di

import com.example.bookworm.common.network.BASE_URL
import com.example.bookworm.common.network.BooksApiService
import com.example.bookworm.feature.books.data.repository.BooksRepositoryImpl
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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
}