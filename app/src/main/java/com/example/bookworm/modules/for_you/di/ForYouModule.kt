package com.example.bookworm.modules.for_you.di

import com.example.bookworm.modules.explore.data.remote.service.ForYouBooksApiService
import com.example.bookworm.common.constants.BASE_URL
import com.example.bookworm.modules.for_you.data.repository.ForYouRepositoryImpl
import com.example.bookworm.modules.for_you.data.source.ForYouBooksRemoteDataSource
import com.example.bookworm.modules.for_you.domain.repository.ForYouRepository
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
    abstract fun bindForYouRepository(forYouRepositoryImpl: ForYouRepositoryImpl): ForYouRepository


    @ViewModelScoped
    @Binds
    abstract fun bindsForYouStateHolder(forYouStateHolderImpl: ForYouStateHolderImpl): ForYouStateHolder

    companion object {

        @ViewModelScoped
        @Provides
        fun provideForYouAPI(): ForYouBooksApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForYouBooksApiService::class.java)
        }

        @ViewModelScoped
        @Provides
        fun provideForYouBooksDataSource(api: ForYouBooksApiService): ForYouBooksRemoteDataSource {
            return ForYouBooksRemoteDataSource(
                booksApiService = api
            )
        }
    }
}