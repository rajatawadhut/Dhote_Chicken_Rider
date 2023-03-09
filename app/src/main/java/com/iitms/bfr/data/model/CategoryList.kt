package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CategoryList(
    @SerializedName("attachments")
    var attachments: ArrayList<Attachments> = arrayListOf(),
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("href")
    var href: String? = null,
    @SerializedName("code")
    var code: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("lastUpdate")
    var lastUpdate: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("createdOn")
    var createdOn: String? = null,
    @SerializedName("createdBy")
    var createdBy: String? = null,
    @SerializedName("lastUpdatedBy")
    var lastUpdatedBy: String? = null,
    @SerializedName("productList")
    var productList: ArrayList<SubCategoryDetail> = arrayListOf(),

    @SerializedName("subCategories")
    var subCategories: ArrayList<CategoryList> = arrayListOf(),

    @SerializedName("isRoot")
    var isRoot: Boolean = false,
    @SerializedName("isLive")
    var isLive: Boolean = false,

    var statusState: Boolean = false

) : Serializable