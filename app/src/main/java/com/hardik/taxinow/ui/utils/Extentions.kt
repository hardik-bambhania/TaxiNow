package com.hardik.taxinow.ui.utils

import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.maps.model.LatLng

fun Geocoder.getAddress(latLng: LatLng): String {
    var address = ""
    try {
        val addressList: List<Address> = this.getFromLocation(latLng.latitude, latLng.longitude, 1)
        if (addressList != null && addressList.isNotEmpty()) {
            val firstAddress = addressList[0]
            address = firstAddress.getAddressLine(0)
        }
    } catch (error: Exception) {
        Log.e("Geocoder", error.message, error)
    }
    return address
}