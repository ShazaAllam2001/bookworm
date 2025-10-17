package com.example.bookworm.modules.explore.di

import com.example.bookworm.common.constants.BASE_URL
import com.example.bookworm.modules.explore.data.remote.service.ExploreBooksApiService
import com.example.bookworm.modules.explore.data.repository.ExploreRepositoryImpl
import com.example.bookworm.modules.explore.data.source.ExploreBooksRemoteDataSource
import com.example.bookworm.modules.explore.domain.repository.ExploreRepository
import com.example.bookworm.modules.explore.presentation.state.ExploreStateHolder
import com.example.bookworm.modules.explore.presentation.ui.ExploreStateHolderImpl
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
abstract class ExploreModule {

    @ViewModelScoped
    @Binds
    abstract fun bindExploreRepository(exploreRepositoryImpl: ExploreRepositoryImpl): ExploreRepository


    @ViewModelScoped
    @Binds
    abstract fun bindsExploreStateHolder(exploreStateHolderImpl: ExploreStateHolderImpl): ExploreStateHolder

    companion object {

        @ViewModelScoped
        @Provides
        fun provideExploreAPI(): ExploreBooksApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExploreBooksApiService::class.java)
        }

        @ViewModelScoped
        @Provides
        fun provideExploreBooksDataSource(api: ExploreBooksApiService): ExploreBooksRemoteDataSource {
            return ExploreBooksRemoteDataSource(
                booksApiService = api
            )
        }
    }
}