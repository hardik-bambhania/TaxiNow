package com.hardik.repository

import com.hardik.repository.model.CityBound
import com.hardik.repository.model.response.GetNearByVehicleListResponse
import com.hardik.repository.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    fun getNearByVehicle(cityBound: CityBound): Flow<GetNearByVehicleListResponse> =
        flow {
            val result = networkDataSource
                .getNearByVehicles(
                    cityBound.startLatitude,
                    cityBound.startLongitude,
                    cityBound.endLatitude,
                    cityBound.endLongitude
                )
            emit(result)
        }

}