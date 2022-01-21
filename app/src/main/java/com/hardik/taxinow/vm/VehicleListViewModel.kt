package com.hardik.taxinow.vm

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardik.repository.Repository
import com.hardik.taxinow.ui.mapper.Mapper
import com.hardik.taxinow.ui.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val geocoder: Geocoder,
    private val repository: Repository
) : ViewModel() {

    val vehicle = repository.getNearByVehicle(Constant.CITY_HAMBURG_BOUND).flowOn(Dispatchers.IO)
        .mapLatest { it.poiList.map { poi -> Mapper.poiToVehicle(poi,geocoder) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}