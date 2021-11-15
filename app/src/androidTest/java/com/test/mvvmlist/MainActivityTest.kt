package com.test.mvvmlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.test.mvvmlist.domain.ItemUseCase
import com.test.mvvmlist.domain.model.ItemResponse
import com.test.mvvmlist.domain.model.ResultData
import com.test.mvvmlist.presentation.activity.MainActivity
import com.test.mvvmlist.presentation.viewmodel.MainViewModel
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule


@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val _response: MutableLiveData<ResultData<ItemResponse>> = MutableLiveData()

    @Mock
    private lateinit var useCase: ItemUseCase

    @Mock
    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var observer: Observer<in ResultData<ItemResponse>>

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setup(){
        viewModel = MainViewModel(useCase)
        `when`(viewModel.response).thenReturn(_response)
    }


    @Test
    fun testComponentVisibilty() = runBlocking {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))

        _response.postValue(ResultData.Success(ItemResponse()))

        assertNotNull(viewModel.response)
        viewModel.response.observeForever(observer)
    }

}