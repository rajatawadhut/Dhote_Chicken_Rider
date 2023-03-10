package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class Banner(
    @SerializedName("bannerList") var bannerList: ArrayList<BannerDetail> = arrayListOf()
)