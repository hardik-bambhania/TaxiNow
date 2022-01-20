package com.hardik.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val id: Int,
    val coordinate: Coordinate,
    val fleetType: FleetType,
    val heading: Float
) : Parcelable
