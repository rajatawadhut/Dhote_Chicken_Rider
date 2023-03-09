package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class DeliverySlotList(
    @SerializedName("deliverDate") var deliverDate: String? = null,
    @SerializedName("slotList") var slotList: ArrayList<DeliveryTime> = arrayListOf()
) : java.io.Serializable