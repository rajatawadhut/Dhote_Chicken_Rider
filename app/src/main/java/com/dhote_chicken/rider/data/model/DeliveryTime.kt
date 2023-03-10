package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class DeliveryTime(

    @SerializedName("slot") var time: String? = null,
    @SerializedName("availability") var availability: Boolean = false,
    @SerializedName("status") var status: Boolean = false

): java.io.Serializable