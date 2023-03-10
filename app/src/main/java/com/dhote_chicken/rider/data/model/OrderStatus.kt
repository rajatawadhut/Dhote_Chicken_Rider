package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class OrderStatus(

    @SerializedName("status")
    var status: String = "Completed"
) : java.io.Serializable