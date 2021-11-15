package com.test.mvvmlist.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvmlist.domain.ItemUseCase
import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.domain.model.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@VisibleForTesting
@HiltViewModel
class MainViewModel @Inject constructor(private var usecase: ItemUseCase) : ViewModel() {
    private val _response: MutableLiveData<ResultData<ItemResponse>> = MutableLiveData()
    val response: LiveData<ResultData<ItemResponse>> = _response

    init {
        _response.value = ResultData.Loading()
        getItems()
    }

    private fun getItems() = viewModelScope.launch {
        _response.value = usecase.getItems()
    }
}