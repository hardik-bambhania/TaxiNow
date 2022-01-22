package com.hardik.repository.network

import com.hardik.repository.model.response.GetNearByVehicleListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface is responsible to fetch data from network
 */
interface NetworkDataSource {

    @GET("/")
    suspend fun getNearByVehicles(
        @Query("p1Lat") startLatitude: Double,
        @Query("p1Lon") startLongitude: Double,
        @Query("p2Lat") endLatitude: Double,
        @Query("p2Lon") endLongitude: Double,
    ): GetNearByVehicleListResponse
}