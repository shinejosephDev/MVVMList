package com.test.mvvmlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.test.mvvmlist.domain.ItemUseCase
import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.domain.model.ResultData
import com.test.mvvmlist.presentation.viewmodel.MainViewModel
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetItemsListTest : TestCase() {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: ItemUseCase

    @Before
    override fun setUp(): Unit = runBlocking {
        super.setUp()
        mainViewModel = MainViewModel(useCase)
        Mockito.verify(useCase).getItems()
    }

    @Test
    fun getListFrom_Network(): Unit = runBlocking {
        Mockito.verify(useCase).getItems()

    }
}