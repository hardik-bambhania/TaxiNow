package com.hardik.taxinow.utils

import android.location.Address
import android.location.Geocoder
import android.view.View
import com.google.android.gms.maps.model.LatLng

/**
 * This class is responsible to manage all extension functions
 */

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Geocoder.getAddress(latLng: LatLng): String {
    var address = ""
    val addressList: List<Address> = this.getFromLocation(latLng.latitude, latLng.longitude, 1)
    if (addressList != null && addressList.isNotEmpty()) {
        val firstAddress = addressList[0]
        address = firstAddress.getAddressLine(0)
    }
    return address
}