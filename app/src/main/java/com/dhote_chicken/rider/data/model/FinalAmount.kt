package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class FinalAmount(
    @SerializedName("orderAmount") var orderAmount: Double = 0.0,
    @SerializedName("extraAmount") var extraAmount: ExtraAmount? = ExtraAmount(),
    @SerializedName("couponAmount") var couponAmount: Double = 0.0,
    @SerializedName("finalOrderAmount") var finalOrderAmount: Double = 0.0
) : java.io.Serializable