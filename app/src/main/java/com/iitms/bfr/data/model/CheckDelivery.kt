package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CheckDelivery(

    @SerializedName("deliveryStatus")
    var deliveryStatus : Boolean = false,

    @SerializedName("message")
    var message : String? = null,
) : Serializable