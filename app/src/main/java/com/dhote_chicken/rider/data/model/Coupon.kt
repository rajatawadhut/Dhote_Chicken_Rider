package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class Coupon(
    @SerializedName("couponList")
    var couponList: ArrayList<CouponList> = arrayListOf()
)