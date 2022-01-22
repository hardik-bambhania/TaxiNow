package com.hardik.taxinow.mapper

import com.hardik.repository.model.Coordinate
import com.hardik.repository.model.FleetType
import com.hardik.repository.model.POI
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test
    fun `POI map to vehicle successfully`() {
        //Given
        val poi = POI(1, Coordinate(1.12, 1.12), FleetType.TAXI, 1.12f)

        //When
        val result = Mapper.poiToVehicle(poi)

        //Then
        Assert.assertEquals(1, result.id)
        Assert.assertEquals(FleetType.TAXI, result.fleetType)
        Assert.assertEquals(1.12f, result.heading)
    }
}