package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class ExtraAmount(

    @SerializedName("packingCharges") var packingCharges: Double = 0.0,
    @SerializedName("deliveryCharges") var deliveryCharges: Double = 0.0
) : java.io.Serializable