package com.iitms.bfr.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitms.bfr.data.Resource
import com.iitms.bfr.data.model.*
import com.iitms.bfr.data.repository.OrderStatusFragmentRepository
import com.iitms.bfr.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderStatusFragmentViewModel @Inject constructor(val repository : OrderStatusFragmentRepository) : BaseViewModel() {

    val orderDataById = MutableLiveData<OrderDataById>()
    val orderDelivered = MutableLiveData<OrderStatus>()
    val userData = MutableLiveData<UserData>()


    fun getUserInfo() : LiveData<UserInfo> {
        return repository.getUserInfo()
    }

    fun getUserData(userId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.getUserData(
                userId
            )) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)

                    if(responseData.value.user != null && responseData.value.user!!.familyName!!.isNotEmpty()){
                        userData.postValue(responseData.value!!)
                    }

                }
                is Resource.Failure -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
                is Resource.AuthenticationFailed -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
            }
        }

    }


    fun getOrderById(orderId : String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.getOrderById(orderId)) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)
                    orderDataById.postValue(responseData.value!!)
                }
                is Resource.Failure -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
                is Resource.AuthenticationFailed -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
            }
        }

    }

    fun deliveredOrder(orderId: String, orderStatus: OrderStatus) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.deliveredOrder(orderId, orderStatus)) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)
                    orderDelivered.postValue(responseData.value!!)
                }
                is Resource.Failure -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
                is Resource.AuthenticationFailed -> {
                    isLoading.postValue(false)
                    failed.postValue(responseData.status)
                }
            }
        }
    }


}