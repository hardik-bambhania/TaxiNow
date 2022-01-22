package com.hardik.taxinow.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hardik.common.model.ApiResult
import com.hardik.repository.Repository
import com.hardik.taxinow.MockData
import com.hardik.taxinow.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class VehicleListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @Test
    fun `get near by vehicles sent Loading status when getting data from server`() = runBlocking {

        //Given
        val viewModel = VehicleListViewModel(repository, dispatcher)

        //When
        val result = viewModel.vehicleList.first()
        Assert.assertEquals(ApiResult.Loading, result)
    }

    @Test
    fun `get near by vehicles from server successfully`() = runBlocking {

        //Given
        every { repository.getNearByVehicle(any()) }
            .returns(flow { emit(MockData.NEAR_BY_VEHICLE) })
        val viewModel = VehicleListViewModel(repository, dispatcher)

        //When
        val result = viewModel.vehicleList.value
        Assert.assertTrue(result is ApiResult.Success)
    }

}