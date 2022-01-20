package com.hardik.taxinow.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hardik.repository.model.Vehicle
import com.hardik.taxinow.databinding.ItemVehicleBinding

class VehicleListAdapter(private var vehicleList: List<Vehicle>) :
    RecyclerView.Adapter<VehicleListAdapter.VehicleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        with(holder.binding) {
            txtVehicleId.text = vehicle.id.toString()
            txtVehicleLocation.text = vehicle.coordinate.latitude.toString()
                .plus(",")
                .plus(vehicle.coordinate.longitude.toString())
            chipVehicleType.text = vehicle.fleetType.name
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