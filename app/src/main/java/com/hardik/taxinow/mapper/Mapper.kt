package com.hardik.taxinow.mapper

import com.hardik.repository.model.POI
import com.hardik.taxinow.model.Vehicle
import com.hardik.taxinow.model.VehicleClass

object Mapper {
    fun poiToVehicle(poi: POI): Vehicle {
        return Vehicle(
            id = poi.id,
            coordinate = poi.coordinate,
            fleetType = poi.fleetType,
            heading = poi.heading,
            vehicleClass = VehicleClass.values().random(),
            address = ""
        )
    }
}