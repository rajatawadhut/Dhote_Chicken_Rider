package com.iitms.bfr.data.repository

import androidx.lifecycle.LiveData
import com.iitms.bfr.data.ApiClient
import com.iitms.bfr.data.db.dao.UserInfoDao
import com.iitms.bfr.data.model.UserInfo
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