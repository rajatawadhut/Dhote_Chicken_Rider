package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class RegisterToken(

    @SerializedName("userMobileToken")
    var userMobileToken : String? =null
) : java.io.Serializable