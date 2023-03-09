package com.iitms.bfr.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iitms.bfr.data.Resource
import com.iitms.bfr.data.model.UserInfo
import com.iitms.bfr.data.model.ValidateOTP
import com.iitms.bfr.data.repository.OtpVerificationRepository
import com.iitms.bfr.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OtpVerificationViewModel @Inject constructor(val repository : OtpVerificationRepository) : BaseViewModel() {

    val validateData = MutableLiveData<ValidateOTP>()

    fun validateUser(phone : String, otp : String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.validateUser(
                phone, otp

            )) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)
                    validateData.postValue(responseData.value!!)
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