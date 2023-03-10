package com.dhote_chicken.rider.data.repository

import androidx.lifecycle.LiveData
import com.dhote_chicken.rider.data.ApiClient
import com.dhote_chicken.rider.data.db.dao.UserInfoDao
import com.dhote_chicken.rider.data.model.OrderStatus
import com.dhote_chicken.rider.data.model.UserInfo
import javax.inject.Inject

class OrderStatusFragmentRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val userInfoDao: UserInfoDao,
) : BaseRepository() {


    fun getUserInfo(): LiveData<UserInfo> {
        return userInfoDao.getUserInfo()
    }
    suspend fun getUserData(userId : String) = safeApiCall {
        apiClient.getUserData( userId)
    }


    suspend fun getOrderById(orderId : String) = safeApiCall {
        apiClient.getOrderById(orderId)
    }
    suspend fun deliveredOrder(orderId: String, orderStatus: OrderStatus) = safeApiCall {
        apiClient.deliveredOrder(orderId, orderStatus)
    }
}