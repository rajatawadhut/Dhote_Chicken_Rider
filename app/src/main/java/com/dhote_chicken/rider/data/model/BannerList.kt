package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class BannerList(
    @SerializedName("id") var id: String? = null,
    @SerializedName("href") var href: String? = null,
    @SerializedName("attachmentType") var attachmentType: String? = null,
    @SerializedName("isRef") var isRef: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null
)