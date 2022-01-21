package com.hardik.taxinow.ui.mapper

import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import com.hardik.repository.model.POI
import com.hardik.taxinow.ui.model.Vehicle
import com.hardik.taxinow.ui.model.VehicleClass
import com.hardik.taxinow.ui.utils.getAddress

object Mapper {
    fun poiToVehicle(poi: POI, geocoder: Geocoder): Vehicle {
        return Vehicle(
            id = poi.id,
            coordinate = poi.coordinate,
            fleetType = poi.fleetType,
            heading = poi.heading,
            vehicleClass = VehicleClass.values().random(),
            address = geocoder.getAddress(LatLng(poi.coordinate.latitude, poi.coordinate.longitude))
        )
    }
}