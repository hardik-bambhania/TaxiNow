package com.hardik.taxinow.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hardik.taxinow.databinding.ItemVehicleBinding
import com.hardik.taxinow.ui.model.Vehicle

class VehicleListAdapter(
    private var vehicleList: List<Vehicle>,
    private val listener: VehicleSelectListener
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
            txtVehicleLocation.text = vehicle.address
            chipVehicleType.text = vehicle.fleetType.name
            imgVehicleClass.setImageResource(vehicle.vehicleClass.icon)

            root.setOnClickListener {
                listener.onVehicleSelect(vehicle)
            }
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

interface VehicleSelectListener {
    fun onVehicleSelect(vehicle: Vehicle)
}