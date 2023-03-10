package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class ContactMedium(
    @SerializedName("email") var email: String? = null,
    @SerializedName("phoneNumber") var phoneNumber: String? = null

)