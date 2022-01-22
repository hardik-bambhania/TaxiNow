package com.hardik.taxinow.model

import android.os.Parcelable
import com.hardik.repository.model.Coordinate
import com.hardik.repository.model.FleetType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val id: Int,
    val coordinate: Coordinate,
    val fleetType: FleetType,
    val heading: Float,
    val vehicleClass: VehicleClass,
    val address: String
) : Parcelable
