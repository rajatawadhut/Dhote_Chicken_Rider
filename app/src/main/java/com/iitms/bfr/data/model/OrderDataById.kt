package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class OrderDataById(
    @SerializedName("order") var order: OrderData? = OrderData()
) : java.io.Serializable