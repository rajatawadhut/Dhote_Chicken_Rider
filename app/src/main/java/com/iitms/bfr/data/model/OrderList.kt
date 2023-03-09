package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class OrderList(
    @SerializedName("deliveryAddress") var deliveryAddress: DeliveryAddress? = DeliveryAddress(),
    @SerializedName("deliveryNotUsed") var deliveryTimeSlot: DeliveryTimeSlot? = DeliveryTimeSlot(),
    @SerializedName("deliveryTimeSlot") var deliveryTime: String? = null,
    @SerializedName("deliveryDate") var deliveryDate: String? = null,
    @SerializedName("paymentMode") var paymentMode: String? = null,
    @SerializedName("paymentStatus") var paymentStatus: String? = null,
    @SerializedName("shoppingCartId") var shoppingCartId: String? = null,
    @SerializedName("couponId") var couponId: String? = null,
    @SerializedName("riderId") var riderId: String? = null,
    @SerializedName("riderName") var riderName: String? = null,
    @SerializedName("totalAmount") var totalAmount: Int? = null,
    @SerializedName("userId") var userId: String? = null
) : java.io.Serializable