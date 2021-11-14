package com.test.mvvmlist.di

import com.test.mvvmlist.data.ListItemsRepository
import com.test.mvvmlist.domain.ItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun providesDataUsecase(repository: ListItemsRepository): ItemUseCase {
        return ItemUseCase(repository)
    }
}