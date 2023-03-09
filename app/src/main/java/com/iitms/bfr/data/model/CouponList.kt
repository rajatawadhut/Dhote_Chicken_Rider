package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class CouponList(
    @SerializedName("id")
    var id: String? = null,
    
    @SerializedName("href") 
    var href: String? = null,
    
    @SerializedName("amount_off") 
    var amountOff: Int = 0,
    
    @SerializedName("max_redemptions") 
    var maxRedemptions: Int = 0,
    
    @SerializedName("name") 
    var name: String? = null,
    
    @SerializedName("times_redeemed") 
    var timesRedeemed: Int = 0,
    
    @SerializedName("status") 
    var status: String? = null,
    
    @SerializedName("createdOn") 
    var createdOn: String? = null,
    
    @SerializedName("lastModified") 
    var lastModified: String? = null
)