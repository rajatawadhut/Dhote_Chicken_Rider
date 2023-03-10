package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class Initial(

    @SerializedName("userInfo")
    var userInfo: UserInfo? = null,

) : Status() {

}