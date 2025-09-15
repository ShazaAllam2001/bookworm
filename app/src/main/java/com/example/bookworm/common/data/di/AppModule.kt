package com.example.bookworm.common.data.di

import com.example.bookworm.feature.settings.data.remote.UserDataSource
import com.example.bookworm.feature.settings.data.repository.UserDataRepositoryImpl
import com.example.bookworm.feature.settings.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

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