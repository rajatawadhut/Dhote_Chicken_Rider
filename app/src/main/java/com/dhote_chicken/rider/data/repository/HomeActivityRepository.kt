package com.dhote_chicken.rider.data.repository

import androidx.lifecycle.LiveData
import com.dhote_chicken.rider.data.ApiClient
import com.dhote_chicken.rider.data.db.dao.UserInfoDao
import com.dhote_chicken.rider.data.model.UserInfo
import javax.inject.Inject

class HomeActivityRepository @Inject constructor(
    private val userInfoDao: UserInfoDao,
    private val apiClient: ApiClient
) : BaseRepository() {

    fun getUserInfo() : LiveData<UserInfo> = userInfoDao.getUserInfo()


}