package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class ShopByCategory(

    @SerializedName("attachments")
    var attachments: ArrayList<Attachments> = arrayListOf(),

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("description")
    var description: String? = null
) : java.io.Serializable