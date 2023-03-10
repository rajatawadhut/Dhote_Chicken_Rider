package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class HomePageData(
    @SerializedName("homePageBannerList")
    var bannerList: ArrayList<BannerDetail> = arrayListOf(),

    @SerializedName("orderAgain")
    var orderAgain: ArrayList<SubCategoryDetail> = arrayListOf(),


    @SerializedName("bestSeller")
    var bestSeller: ArrayList<SubCategoryDetail> = arrayListOf(),


    @SerializedName("shopByCategory")
    var shopByCategory: ArrayList<ShopByCategory> = arrayListOf()


) : java.io.Serializable