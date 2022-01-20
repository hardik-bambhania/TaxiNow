package com.hardik.taxinow.vm

import androidx.lifecycle.ViewModel
import com.hardik.common.model.ApiResult
import com.hardik.repository.Repository
import com.hardik.repository.model.CityBound
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val vehicle = repository.getNearByVehicle(
        CityBound(
            53.694865,
            9.757589, 53.394655, 10.099891
        )
    ).catch {
        emit(ApiResult.Error(it.message ?: "Somthing went wrong"))
    }.flowOn(Dispatchers.IO)

}