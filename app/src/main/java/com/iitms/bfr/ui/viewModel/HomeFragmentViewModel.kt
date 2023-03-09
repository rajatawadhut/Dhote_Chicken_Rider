package com.iitms.bfr.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.iitms.bfr.data.Resource
import com.iitms.bfr.data.model.*
import com.iitms.bfr.data.repository.HomeFragmentRepository
import com.iitms.bfr.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(val repository : HomeFragmentRepository) : BaseViewModel() {

    val homePageOrder = MutableLiveData<HomePageOrder>()
    val userData = MutableLiveData<UserData>()
    var cartData = MutableLiveData<Cart>()
    var banner = MutableLiveData<Banner>()


    fun getUserInfo() : LiveData<UserInfo> {
        return repository.getUserInfo()
    }

    fun getOrderList(userId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val responseData = repository.getOrderList(userId)) {
                is Resource.Loading -> {
                    val isLoading: Boolean = responseData.value
                }
                is Resource.Success -> {
                    isLoading.postValue(false)
                    homePageOrder.postValue(responseData.value!!)
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
                        val userInfo = UserInfo()
                        userInfo.listToJson = Gson().toJson(responseData.value.user).toString()
                        saveUserInfo(userInfo)
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


    private fun saveUserInfo(data: UserInfo?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) { repository.deleteUserInfo() }
            if (data != null) {
                withContext(Dispatchers.Default)  { repository.saveUserInfo(data!!) }
            }
        }
    }


}