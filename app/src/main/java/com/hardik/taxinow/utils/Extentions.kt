package com.hardik.taxinow.utils

import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng

fun Geocoder.getAddress(latLng: LatLng): String {
    var address = ""
    val addressList: List<Address> = this.getFromLocation(latLng.latitude, latLng.longitude, 1)
    if (addressList != null && addressList.isNotEmpty()) {
        val firstAddress = addressList[0]
        address = firstAddress.getAddressLine(-1)
    }
    return address
}