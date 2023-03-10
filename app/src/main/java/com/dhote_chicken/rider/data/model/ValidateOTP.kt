package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class ValidateOTP(
    @SerializedName("accessToken")
    var accessToken: String? = null,
    @SerializedName("refreshToken")
    var refreshToken: String? = null,
    @SerializedName("customerId")
    var customerId: String? = null
) : java.io.Serializable