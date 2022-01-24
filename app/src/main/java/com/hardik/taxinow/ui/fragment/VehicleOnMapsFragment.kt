package com.hardik.taxinow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.hardik.repository.model.FleetType
import com.hardik.taxinow.R
import com.hardik.taxinow.databinding.FragmentVehicleOnMapsBinding
import com.hardik.taxinow.utils.Constant
import com.hardik.taxinow.utils.toStringWithFourPrecision
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleOnMapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentVehicleOnMapsBinding
    private val args: VehicleOnMapsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleOnMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLngBounds = LatLngBounds.Builder()
        args.vehicleList.forEach { vehicle ->
            val latLng = LatLng(vehicle.coordinate.latitude, vehicle.coordinate.longitude)
            val markerTitle = "${Constant.CITY_HAMBURG_CODE}-${vehicle.id.toString().dropLast(2)}"
            val marker = MarkerOptions()
                .position(latLng)
                .title(markerTitle.plus("(${vehicle.fleetType.name}-${vehicle.vehicleClass.name})"))
                .snippet(
                    getString(
                        R.string.location,
                        vehicle.coordinate.latitude.toStringWithFourPrecision(),
                        vehicle.coordinate.longitude.toStringWithFourPrecision()
                    )
                )
                .flat(true)
                .rotation(vehicle.heading)
            if (vehicle.fleetType == FleetType.TAXI) {
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_taxi))
            } else {
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pooling))
            }

            googleMap.addMarker(marker)
            latLngBounds.include(latLng)
        }

        args.selectedVehicle?.let { vehicle ->
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        vehicle.coordinate.latitude,
                        vehicle.coordinate.longitude
                    ), 15F
                )
            )
        } ?: kotlin.run {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 50))
        }
    }
}