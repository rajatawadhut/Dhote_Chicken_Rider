package com.dhote_chicken.rider.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "UserInfo")
class UserInfo(): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @SerializedName("user")
    var listToJson : String? = null

}
