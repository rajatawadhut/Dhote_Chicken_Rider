package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class DeliveryTimeSlot(
    @SerializedName("deliveryDate") var deliveryDate: String? = null,
    @SerializedName("deliveryTimeSlot") var deliveryTimeSlot: String? = null
) : java.io.Serializable