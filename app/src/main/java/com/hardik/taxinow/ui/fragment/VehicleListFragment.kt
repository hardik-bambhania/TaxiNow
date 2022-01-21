package com.hardik.taxinow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hardik.taxinow.databinding.FragmentVehicleListBinding
import com.hardik.taxinow.ui.adapter.VehicleListAdapter
import com.hardik.taxinow.ui.adapter.VehicleSelectListener
import com.hardik.taxinow.ui.model.Vehicle
import com.hardik.taxinow.vm.VehicleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VehicleListFragment : Fragment(), VehicleSelectListener {

    private lateinit var binding: FragmentVehicleListBinding
    private val viewModel: VehicleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        addObserver()
        addListeners()
    }

    private fun addListeners() {
        binding.fabMap.setOnClickListener {
            lifecycleScope.launch {
                val vehicles = viewModel.vehicle.value
                vehicles?.let { vehicleList ->
                    val direction =
                        VehicleListFragmentDirections.actionVehicleListFragmentToVehicleOnMapsFragment(
                            null, vehicleList.toTypedArray()
                        )
                    findNavController().navigate(direction)
                }
            }
        }
    }

    private fun addObserver() {
        lifecycleScope.launch {
            viewModel.vehicle.collectLatest {
                binding.progressBar.visibility = View.GONE
                it?.let { vehicleList ->
                    if (vehicleList.isNotEmpty()) {
                        binding.fabMap.show()
                        (binding.recyclerViewVehicle.adapter as VehicleListAdapter)
                            .refresh(vehicleList)
                    } else {
                        binding.fabMap.hide()
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.recyclerViewVehicle.adapter = VehicleListAdapter(emptyList(), this)
    }

    override fun onVehicleSelect(vehicle: Vehicle) {
        val vehicles = viewModel.vehicle.value
        vehicles?.let { vehicleList ->
            val direction =
                VehicleListFragmentDirections.actionVehicleListFragmentToVehicleOnMapsFragment(
                    vehicle, vehicleList.toTypedArray()
                )
            findNavController().navigate(direction)
        }
    }

}