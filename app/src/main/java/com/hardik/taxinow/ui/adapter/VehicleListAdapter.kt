package com.hardik.taxinow.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.hardik.repository.model.Vehicle
import com.hardik.taxinow.databinding.ItemVehicleBinding
import com.hardik.taxinow.ui.model.VehicleClass
import com.hardik.taxinow.ui.utils.getAddress

class VehicleListAdapter(
    private val context: Context,
    private var vehicleList: List<Vehicle>
) :
    RecyclerView.Adapter<VehicleListAdapter.VehicleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        with(holder.binding) {
            txtVehicleId.text = "HH-${vehicle.id.toString().dropLast(2)}"
            txtVehicleLocation.text =
                context.getAddress(
                    LatLng(
                        vehicle.coordinate.latitude,
                        vehicle.coordinate.longitude
                    )
                )
            chipVehicleType.text = vehicle.fleetType.name
            imgVehicleClass.setImageResource(VehicleClass.values().random().icon)
        }
    }

    override fun getItemCount(): Int {
        return vehicleList.size
    }

    fun refresh(newVehicleList: List<Vehicle>) {
        vehicleList = newVehicleList
        notifyDataSetChanged()
    }

    class VehicleListViewHolder(val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root)
}