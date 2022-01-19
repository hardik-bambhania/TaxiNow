package com.hardik.repository.model

data class Vehicle(
    val id: Int,
    val coordinate: Coordinate,
    val fleetType: FleetType,
    val heading: Float
)
