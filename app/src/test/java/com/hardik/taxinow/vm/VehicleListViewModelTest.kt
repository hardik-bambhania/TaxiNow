package com.hardik.taxinow.vm

import android.location.Geocoder
import com.hardik.common.model.ApiResult
import com.hardik.repository.Repository
import com.hardik.taxinow.MockData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VehicleListViewModelTest {

    @MockK
    lateinit var repository: Repository

   /* @MockK
    lateinit var geocoder: Geocoder*/

    lateinit var viewModel: VehicleListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = VehicleListViewModel(Geocoder(null), repository)
    }


    @Test
    fun `get near by vehicles successfully`() = runBlocking {

        //Given
        every { repository.getNearByVehicle(any()) }
            .returns(flow { emit(MockData.NEAR_BY_VEHICLE) })

        //When
        val result = viewModel.vehicleList.first()
        Assert.assertEquals(ApiResult.Loading, result)
    }

}