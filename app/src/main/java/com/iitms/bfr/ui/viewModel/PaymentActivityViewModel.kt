package com.iitms.bfr.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iitms.bfr.data.Resource
import com.iitms.bfr.data.model.*
import com.iitms.bfr.data.repository.LoginRepository
import com.iitms.bfr.data.repository.PaymentRepository
import com.iitms.bfr.ui.base.BaseViewModel
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