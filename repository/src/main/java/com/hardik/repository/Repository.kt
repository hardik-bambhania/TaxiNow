package com.hardik.repository

import com.hardik.repository.model.CityBound
import com.hardik.repository.model.response.GetNearByVehicleListResponse
import com.hardik.repository.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This class is responsible to provide and data to ViewModel layer
 */
class Repository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {

    /**
     * Get near by vehicles for given City bound from backend
     * @param cityBound : City Bounds
     */
    fun getNearByVehicle(cityBound: CityBound): Flow<GetNearByVehicleListResponse> = flow {
        val result = networkDataSource.getNearByVehicles(
            cityBound.startLatitude,
            cityBound.startLongitude,
            cityBound.endLatitude,
            cityBound.endLongitude
        )
        emit(result)
    }

}