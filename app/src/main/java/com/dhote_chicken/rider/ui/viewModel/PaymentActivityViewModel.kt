package com.dhote_chicken.rider.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dhote_chicken.rider.data.Resource
import com.dhote_chicken.rider.data.model.*
import com.dhote_chicken.rider.data.repository.PaymentRepository
import com.dhote_chicken.rider.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentActivityViewModel @Inject constructor(val repository : PaymentRepository) : BaseViewModel() {

    val placedOrder = MutableLiveData<Status>()

    fun placedOrder(order: OrderList) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.placedOrder(
                order
            )) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)
                    placedOrder.postValue(responseData.value!!)
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

    private fun saveUserInfo(data: UserInfo?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) { repository.deleteUserInfo() }
            if (data != null) {
                withContext(Dispatchers.Default)  { repository.saveUserInfo(data!!) }
            }
        }
    }

}