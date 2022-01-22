package com.hardik.taxinow.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardik.common.logger.AppLogger
import com.hardik.common.model.ApiResult
import com.hardik.repository.Repository
import com.hardik.taxinow.di.IoDispatcher
import com.hardik.taxinow.mapper.Mapper
import com.hardik.taxinow.model.Vehicle
import com.hardik.taxinow.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val repository: Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
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
        viewModelScope.launch(dispatcher) {
            repository.getNearByVehicle(Constant.CITY_HAMBURG_BOUND)
                .catch {
                    AppLogger.error(VehicleListViewModel::class.java.name, it)
                    _vehicle.emit(ApiResult.Error(it.message))
                }.collectLatest {
                    val result = it.poiList.map { poi -> Mapper.poiToVehicle(poi) }
                    _vehicle.emit(ApiResult.Success(result))
                }
        }
    }
}