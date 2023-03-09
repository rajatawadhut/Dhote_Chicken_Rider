package com.iitms.bfr.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CartDetails")
class CartDetails(): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("CartData")
    var listToJson : String? = null
}