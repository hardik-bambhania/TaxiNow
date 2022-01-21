package com.hardik.repository.model.response

import android.os.Parcelable
import com.hardik.repository.model.POI
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetNearByVehicleListResponse(
    val poiList: List<POI>
) : Parcelable
