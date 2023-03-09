package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class ShoppingCart(
    @SerializedName("shoppingCart")
    var shoppingCart: Cart? = Cart()
) : java.io.Serializable