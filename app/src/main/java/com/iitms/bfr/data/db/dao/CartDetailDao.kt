package com.iitms.bfr.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iitms.bfr.data.model.CartDetails
import com.iitms.bfr.data.model.UserInfo

@Dao
interface CartDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartDetails(cartDetails: CartDetails): Long

    @Query("SELECT * FROM CartDetails")
    fun getCartDetails(): LiveData<CartDetails>

    @Query("DELETE FROM CartDetails")
    fun deleteCartDetails(): Int

}
