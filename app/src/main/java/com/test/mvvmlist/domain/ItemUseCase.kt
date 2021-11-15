package com.test.mvvmlist.domain

import com.test.mvvmlist.data.ListItemsRepository
import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.domain.model.ResultData
import javax.inject.Inject

class ItemUseCase @Inject constructor(private val repository: ListItemsRepository) {
    suspend fun getItems() : ResultData<ItemResponse> {
        return repository.getItemList()
    }
}