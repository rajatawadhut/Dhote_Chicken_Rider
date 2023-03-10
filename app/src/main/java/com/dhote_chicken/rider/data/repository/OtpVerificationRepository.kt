package com.dhote_chicken.rider.data.repository

import androidx.lifecycle.LiveData
import com.dhote_chicken.rider.data.ApiClient
import com.dhote_chicken.rider.data.db.dao.UserInfoDao
import com.dhote_chicken.rider.data.model.UserInfo
import javax.inject.Inject

class OtpVerificationRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val userInfoDao: UserInfoDao
) : BaseRepository() {

    suspend  fun validateUser(phone : String, otp : String) = safeApiCall {
        apiClient.validateUser(phone, otp)
    }
    suspend fun saveUserInfo(userInfo: UserInfo) = userInfoDao.insertUserData(userInfo)

    fun deleteUserInfo() = userInfoDao.deleteUserInfo()

    fun getUserInfo(): LiveData<UserInfo> {
        return userInfoDao.getUserInfo()
    }
}