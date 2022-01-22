package com.hardik.taxinow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hardik.common.model.ApiResult
import com.hardik.taxinow.databinding.FragmentVehicleListBinding
import com.hardik.taxinow.model.Vehicle
import com.hardik.taxinow.ui.adapter.VehicleListAdapter
import com.hardik.taxinow.ui.adapter.VehicleSelectListener
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
                val vehicles = viewModel.vehicleList.value
                if (vehicles is ApiResult.Success) {
                    vehicles.data?.let { vehicleList ->
                        val direction =
                            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleOnMapsFragment(
                                null, vehicleList.toTypedArray()
                            )
                        findNavController().navigate(direction)
                    }
                }
            }
        }
    }

    private fun addObserver() {
        lifecycleScope.launch {
            viewModel.vehicleList.collectLatest { response ->
                when (response) {
                    is ApiResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (response.data.isNotEmpty()) {
                            binding.fabMap.show()
                            (binding.recyclerViewVehicle.adapter as VehicleListAdapter)
                                .refresh(response.data)
                        } else {
                            binding.fabMap.hide()
                        }
                    }
                    is ApiResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.recyclerViewVehicle.adapter = VehicleListAdapter(emptyList(), this)
    }

    override fun onVehicleSelect(vehicle: Vehicle) {
        val apiResponse = viewModel.vehicleList.value
        if (apiResponse is ApiResult.Success) {
            apiResponse.data?.let { vehicleList ->
                val direction =
                    VehicleListFragmentDirections.actionVehicleListFragmentToVehicleOnMapsFragment(
                        vehicle, vehicleList.toTypedArray()
                    )
                findNavController().navigate(direction)
            }
        }
    }

}