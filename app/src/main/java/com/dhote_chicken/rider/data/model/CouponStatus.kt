package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class CouponStatus(
    @SerializedName("couponStatus")
    var couponStatus: Boolean = false,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("amount_off")
    var amountOff: Double = 0.0

) : java.io.Serializable