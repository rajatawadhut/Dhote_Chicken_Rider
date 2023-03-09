package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class Cart(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("href")
    var href: String? = null,

    @SerializedName("cartItems")
    var cartItems: ArrayList<CartItems> = arrayListOf(),

    @SerializedName("customerId")
    var customerId: String? = null,

    @SerializedName("couponId")
    var couponId: String? = null,

    @SerializedName("couponName")
    var couponName: String? = null,

    @SerializedName("totalPrice")
    var totalPrice: Double = 0.0,

    @SerializedName("createdOn")
    var createdOn: String? = null,

    @SerializedName("lastModified")
    var lastModified: String? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("finalAmount")
    var finalAmount: FinalAmount = FinalAmount(),

    @SerializedName("deliverySlotList" )
    var deliverySlotList : ArrayList<DeliverySlotList> ?= null



) : java.io.Serializable