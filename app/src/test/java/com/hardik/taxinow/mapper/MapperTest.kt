package com.hardik.taxinow.mapper

import android.location.Geocoder
import com.hardik.repository.model.Coordinate
import com.hardik.repository.model.FleetType
import com.hardik.repository.model.POI
import com.hardik.taxinow.utils.getAddress
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @MockK
    lateinit var geocoder: Geocoder

    @Test
    fun `convert data`() {
        //Given
        every { geocoder.getAddress(any()) }.returns("Vadodara,India")
        val poi = POI(1, Coordinate(1.12, 1.12), FleetType.TAXI, 1.12f)

        //When
        val result = Mapper.poiToVehicle(poi, geocoder)

        //Then
        Assert.assertNotNull(result)

    }
}