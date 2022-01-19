package com.hardik.repository.model.response

import com.hardik.repository.model.Vehicle

data class GetNearByVehicleListResponse(
    val poiList: List<Vehicle>
)
