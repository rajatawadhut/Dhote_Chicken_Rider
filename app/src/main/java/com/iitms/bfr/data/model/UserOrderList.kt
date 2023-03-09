package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class UserOrderList(
    @SerializedName("orderList") var orderList: ArrayList<OrderData> = arrayListOf()
) : java.io.Serializable