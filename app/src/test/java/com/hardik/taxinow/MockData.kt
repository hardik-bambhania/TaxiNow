package com.hardik.taxinow

import com.hardik.repository.model.CityBound
import com.hardik.repository.model.Coordinate
import com.hardik.repository.model.FleetType
import com.hardik.repository.model.POI
import com.hardik.repository.model.response.GetNearByVehicleListResponse

object MockData {
    val CITY_HAMBURG_BOUND = CityBound(53.694865, 9.757589, 53.394655, 10.099891)
    val NEAR_BY_VEHICLE = GetNearByVehicleListResponse(
        listOf(
            POI(
                id = 1212,
                coordinate = Coordinate(23.1234, 45.7896),
                fleetType = FleetType.TAXI,
                heading = 123.7894f
            )
        )
    )

}