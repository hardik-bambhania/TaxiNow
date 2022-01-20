package com.hardik.taxinow.ui.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.util.*

fun Context.getAddress(latLng: LatLng): String {
    val mGeocoder = Geocoder(this, Locale.getDefault())
    var address = ""

    val addressList: List<Address> = mGeocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
    if (addressList != null && addressList.isNotEmpty()) {
        val firstAddress = addressList[0]
        address = firstAddress.getAddressLine(0)
    }
    return address
}