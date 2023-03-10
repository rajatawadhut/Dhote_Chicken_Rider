package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class OrderData(
    @SerializedName("id") var id: String? = null,
    @SerializedName("href") var href: String? = null,
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("shoppingCartId") var shoppingCartId: String? = null,
    @SerializedName("totalOrderCost") var totalOrderCost: Int? = null,
    @SerializedName("deliveryAddress") var deliveryAddress: DeliveryAddress? = DeliveryAddress(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("deliveryTimeSlot") var deliveryTimeSlot: DeliveryTimeSlot? = null,
    @SerializedName("createdOn") var createdOn: String? = null,
    @SerializedName("order_id") var order_id: String? = null,
    @SerializedName("lastModified") var lastModified: String? = null,
    @SerializedName("createdBy") var createdBy: String? = null,
    @SerializedName("lastUpdatedBy") var lastUpdatedBy: String? = null,
    @SerializedName("paymentMode") var paymentMode: String? = null,
    @SerializedName("paymentStatus") var paymentStatus: String? = null,
    @SerializedName("couponCode") var couponCode: String? = null,
    @SerializedName("couponName") var couponName: String? = null,
    @SerializedName("riderId") var riderId: String? = null,
    @SerializedName("riderName") var riderName: String? = null,
    @SerializedName("product") var product: ArrayList<SubCategoryDetail> = arrayListOf(),
    @SerializedName("finalAmount") var finalAmount: FinalAmount = FinalAmount(),
    @SerializedName("orderLifeCycleStatus") var orderLifeCycleStatus: ArrayList<OrderLifeCycleStatus> = arrayListOf(),
    @SerializedName("shoppingCart") var shoppingCart: Cart? = null
) : java.io.Serializable