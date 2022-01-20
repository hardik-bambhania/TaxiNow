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
import com.hardik.taxinow.ui.adapter.VehicleListAdapter
import com.hardik.taxinow.vm.VehicleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VehicleListFragment : Fragment() {

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
                val vehicles = viewModel.vehicle.last()
                if (vehicles is ApiResult.Success) {
                    vehicles.data?.let { vehicleList ->
                        val direction =
                            VehicleListFragmentDirections.actionVehicleListFragmentToVehicleOnMapsFragment(
                                vehicleList
                            )
                        findNavController().navigate(direction)
                    }
                }
            }

        }
    }

    private fun addObserver() {
        lifecycleScope.launch {
            viewModel.vehicle.collectLatest {
                when (it) {
                    is ApiResult.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiResult.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { getNearByVehicleListResponse ->
                            if (getNearByVehicleListResponse.poiList.isNotEmpty()) {
                                binding.fabMap.show()
                                (binding.recyclerViewVehicle.adapter as VehicleListAdapter)
                                    .refresh(getNearByVehicleListResponse.poiList)
                            } else {
                                binding.fabMap.hide()
                            }
                        }
                    }
                    is ApiResult.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun initUI() {
        binding.recyclerViewVehicle.adapter = VehicleListAdapter(requireContext(), emptyList())
    }

}