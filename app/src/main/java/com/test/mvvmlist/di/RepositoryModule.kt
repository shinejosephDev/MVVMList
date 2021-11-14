package com.test.mvvmlist.di

import com.test.mvvmlist.data.ListItemsRepository
import com.test.mvvmlist.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun providesSearchRepo(service: ApiService): ListItemsRepository {
        return ListItemsRepository(service = service)
    }
}