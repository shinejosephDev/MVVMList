package com.test.mvvmlist.data

import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.domain.model.ResultData
import com.test.mvvmlist.network.ApiService
import javax.inject.Inject

class ListItemsRepository @Inject constructor(private val service: ApiService) {
    suspend fun getItemList(): ResultData<ItemResponse> {
        try {
            return ResultData.Success(service.getItemList())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            return ResultData.Error("Failed to load data")
        }
    }
}