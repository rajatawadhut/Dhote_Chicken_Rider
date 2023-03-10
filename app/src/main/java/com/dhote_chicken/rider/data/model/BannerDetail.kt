package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class BannerDetail(
    @SerializedName("id") var id: String? = null,
    @SerializedName("href") var href: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("createdOn") var createdOn: String? = null,
    @SerializedName("lastModifiedDate") var lastModifiedDate: String? = null,
    @SerializedName("attachments") var attachments: ArrayList<Attachments> = arrayListOf()
)