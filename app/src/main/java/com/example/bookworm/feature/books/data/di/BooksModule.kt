package com.example.bookworm.feature.books.data.di

import com.example.bookworm.common.constants.BASE_URL
import com.example.bookworm.feature.books.data.remote.BooksApiService
import com.example.bookworm.feature.books.data.remote.BooksDataSource
import com.example.bookworm.feature.books.data.repository.BooksRepositoryImpl
import com.example.bookworm.feature.books.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object BooksModule {

    @Provides
    @ViewModelScoped
    fun provideAPI(): BooksApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApiService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideBooksDataSource(api: BooksApiService): BooksDataSource {
        return BooksDataSource(
            booksApi = api
        )
    }

    @Provides
    @ViewModelScoped
    fun provideBooksRepo(booksDatasource: BooksDataSource): BooksRepository {
        return BooksRepositoryImpl(
            booksRemoteDataSource = booksDatasource
        )
    }
}