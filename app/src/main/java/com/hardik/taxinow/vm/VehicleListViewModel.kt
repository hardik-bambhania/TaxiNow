package com.hardik.taxinow.vm

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardik.common.model.ApiResult
import com.hardik.repository.Repository
import com.hardik.taxinow.mapper.Mapper
import com.hardik.taxinow.model.Vehicle
import com.hardik.taxinow.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val geocoder: Geocoder,
    private val repository: Repository
) : ViewModel() {

    private val _vehicle = MutableStateFlow<ApiResult<List<Vehicle>>>(ApiResult.Loading)
    val vehicleList = _vehicle.asStateFlow()

    init {
        getNearByVehicle()
    }

    /**
     * Get near by vehicle list from server
     */
    private fun getNearByVehicle() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getNearByVehicle(Constant.CITY_HAMBURG_BOUND)
                .catch { _vehicle.emit(ApiResult.Error(it.message)) }
                .collectLatest {
                    val result = it.poiList.map { poi -> Mapper.poiToVehicle(poi, geocoder) }
                    _vehicle.emit(ApiResult.Success(result))
                }
        }
    }
}