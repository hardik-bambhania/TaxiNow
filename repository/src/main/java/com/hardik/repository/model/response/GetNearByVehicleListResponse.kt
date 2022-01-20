package com.hardik.repository.model.response

import android.os.Parcelable
import com.hardik.repository.model.Vehicle
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetNearByVehicleListResponse(
    val poiList: List<Vehicle>
) : Parcelable
