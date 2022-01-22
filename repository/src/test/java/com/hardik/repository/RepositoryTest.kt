package com.hardik.repository

import com.hardik.repository.model.FleetType
import com.hardik.repository.model.response.GetNearByVehicleListResponse
import com.hardik.repository.network.NetworkDataSource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    @MockK
    lateinit var networkDataSource: NetworkDataSource

    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = Repository(networkDataSource)
    }

    @Test
    fun `Get nearby vehicle from server successfully`() = runBlocking {
        //Given
        every {
            runBlocking {
                networkDataSource.getNearByVehicles(any(), any(), any(), any())
            }
        }.returns(MockData.RESPONSE_NEAR_BY_VEHICLE)

        //When
        val result = repository.getNearByVehicle(MockData.CITY_HAMBURG_BOUND).first()

        Assert.assertEquals(1, result.poiList.size)
        Assert.assertEquals(1212, result.poiList[0].id)
        Assert.assertEquals(123.7894f, result.poiList[0].heading)
        Assert.assertEquals(FleetType.TAXI, result.poiList[0].fleetType)
    }

    @Test
    fun `No nearby vehicle found`() = runBlocking {
        //Given
        every {
            runBlocking {
                networkDataSource.getNearByVehicles(any(), any(), any(), any())
            }
        }.returns(GetNearByVehicleListResponse(emptyList()))

        //When
        val result = repository.getNearByVehicle(MockData.CITY_HAMBURG_BOUND).first()

        Assert.assertEquals(0, result.poiList.size)
    }
}