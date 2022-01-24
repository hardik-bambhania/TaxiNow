package com.hardik.taxinow

import com.hardik.repository.model.Coordinate
import com.hardik.repository.model.FleetType
import com.hardik.repository.model.POI
import com.hardik.repository.model.response.GetNearByVehicleListResponse

object MockData {
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