package com.example.bookworm.modules.for_you.di

import com.example.bookworm.common.constants.BASE_URL
import com.example.bookworm.feature.books.data.remote.BooksDataSource
import com.example.bookworm.modules.for_you.data.remote.service.BooksApiService
import com.example.bookworm.modules.for_you.data.repository.BooksRepositoryImpl
import com.example.bookworm.modules.for_you.data.source.BooksRemoteDataSource
import com.example.bookworm.modules.for_you.domain.repository.BooksRepository
import com.example.bookworm.modules.for_you.presentation.state.ForYouStateHolder
import com.example.bookworm.modules.for_you.presentation.ui.ForYouStateHolderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
abstract class ForYouModule {

    @ViewModelScoped
    @Binds
    abstract fun bindForYouRepository(forYouRepositoryImpl: BooksRepositoryImpl): BooksRepository


    @ViewModelScoped
    @Binds
    abstract fun bindsForYouStateHolder(forYouStateHolderImpl: ForYouStateHolderImpl): ForYouStateHolder

    companion object {

        @ViewModelScoped
        @Provides
        fun provideForYouAPI(): BooksApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksApiService::class.java)
        }

        @ViewModelScoped
        @Provides
        fun provideBooksDataSource(api: BooksApiService): BooksRemoteDataSource {
            return BooksRemoteDataSource(
                booksApiService = api
            )
        }
    }
}