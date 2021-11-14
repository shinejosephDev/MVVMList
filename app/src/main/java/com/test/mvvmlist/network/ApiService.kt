package com.test.mvvmlist.network

import com.test.mvvmlist.domain.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("default/dynamodb-writer")
    suspend fun getItemList() : ItemResponse
}