package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Category(

    @SerializedName("categoryList")
    var categoryList: ArrayList<CategoryList> = arrayListOf()


) : Serializable