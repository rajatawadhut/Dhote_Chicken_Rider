package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class CartItems(
    @SerializedName("quantity") var quantity: Int = 0,
    @SerializedName("notes") var notes: String? = null,
    @SerializedName("itemTotalPrice") var itemTotalPrice: Double = 0.0,
    @SerializedName("itemPrice") var itemPrice: Double = 0.0,
    @SerializedName("discountPrice") var discountPrice: Double = 0.0,
    @SerializedName("productId") var productId: String? = null,
    @SerializedName("productName") var productName: String? = null,
    @SerializedName("liveChickenPreparation") var liveChickenPreparation: ChickenPreparationWeight? = null
) : java.io.Serializable