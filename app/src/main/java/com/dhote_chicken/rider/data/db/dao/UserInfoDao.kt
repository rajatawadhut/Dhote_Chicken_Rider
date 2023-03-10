package com.dhote_chicken.rider.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dhote_chicken.rider.data.model.UserInfo

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userInfo: UserInfo): Long

    @Query("SELECT * FROM UserInfo")
    fun getUserInfo(): LiveData<UserInfo>

    @Query("DELETE FROM UserInfo")
    fun deleteUserInfo(): Int

}
