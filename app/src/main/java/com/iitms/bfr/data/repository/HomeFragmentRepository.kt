package com.iitms.bfr.data.repository

import androidx.lifecycle.LiveData
import com.iitms.bfr.data.ApiClient
import com.iitms.bfr.data.db.dao.CartDetailDao
import com.iitms.bfr.data.db.dao.UserInfoDao
import com.iitms.bfr.data.model.CartDetails
import com.iitms.bfr.data.model.User
import com.iitms.bfr.data.model.UserInfo
import javax.inject.Inject

class HomeFragmentRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val userInfoDao: UserInfoDao,
    private val cartDetailDao: CartDetailDao
    ) : BaseRepository() {


    fun getUserInfo(): LiveData<UserInfo> {
        return userInfoDao.getUserInfo()
    }

    suspend fun saveUserInfo(userInfo: UserInfo) = userInfoDao.insertUserData(userInfo)

    fun deleteUserInfo() = userInfoDao.deleteUserInfo()


    suspend fun saveUserDate(user : User) = safeApiCall {
        apiClient.saveUserDate( user)
    }

    suspend  fun getCategory() = safeApiCall {
        apiClient.getCategory()
    }

    suspend fun getOrderList(userId: String) = safeApiCall {
        apiClient.getOrderList(userId)
    }

    suspend fun getUserData(userId : String) = safeApiCall {
        apiClient.getUserData( userId)
    }


    suspend fun getBanner() = safeApiCall {
        apiClient.getBanner("DESC")
    }


    suspend fun getCartDataFromServer(userId : String) = safeApiCall {
        apiClient.getCartDataFromServer( userId)
    }



    fun getCartData(): LiveData<CartDetails> {
        return cartDetailDao.getCartDetails()
    }

    suspend fun saveCartData(cartDetails: CartDetails) = cartDetailDao.insertCartDetails(cartDetails)

    fun deleteCartData() = cartDetailDao.deleteCartDetails()

}