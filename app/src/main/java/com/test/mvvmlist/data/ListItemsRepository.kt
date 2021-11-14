package com.test.mvvmlist.data

import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.network.ApiService
import javax.inject.Inject

class ListItemsRepository @Inject constructor(private val service: ApiService){
    suspend fun getItemList() : ItemResponse {
        return service.getItemList()
    }
}