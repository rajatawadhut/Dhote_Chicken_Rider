package com.dhote_chicken.rider.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dhote_chicken.rider.data.db.dao.*
import com.dhote_chicken.rider.data.model.CartDetails
import com.dhote_chicken.rider.data.model.UserInfo

@Database(
    entities = [UserInfo::class, CartDetails::class],
    version = 1,
    exportSchema = false
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun userInfo(): UserInfoDao
    abstract fun cartDetails(): CartDetailDao
}