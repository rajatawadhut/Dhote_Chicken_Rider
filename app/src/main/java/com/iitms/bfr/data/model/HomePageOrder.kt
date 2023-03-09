package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class HomePageOrder(
    @SerializedName("inProgress")
    var inProgress: ArrayList<OrderData> = arrayListOf(),
    @SerializedName("completedOrder")
    var completedOrder: ArrayList<OrderData> = arrayListOf()

) : java.io.Serializable