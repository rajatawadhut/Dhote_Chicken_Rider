package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class SubCategoryDetail(
    @SerializedName("attachments")
    var attachments: ArrayList<Attachments> = arrayListOf(),

    @SerializedName("liveChickenPreparation")
    var liveChickenPreparation: ChickenPreparation? = null,

    @SerializedName("createdOn")
    var createdOn: String? = null,

    @SerializedName("lastModified")
    var lastModified: String? = null,

    @SerializedName("createdBy")
    var createdBy: String? = null,

    @SerializedName("lastUpdatedBy")
    var lastUpdatedBy: String? = null,

    @SerializedName("status")
    var status: String? = null,

    @SerializedName("tag")
    var tag: String? = null,

    @SerializedName("noOfOrders")
    var noOfOrders: Int = 0,

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

    @SerializedName("discount")
    var discount: Double = 0.0,

    @SerializedName("discountPercentage")
    var discountPercentage: Int = 0,

    @SerializedName("categoryId")
    var categoryId: String? = null,

    @SerializedName("originalPrice")
    var originalPrice: Double = 0.0,

    @SerializedName("piecesSize")
    var piecesSize: String? = null,

    @SerializedName("originalWeight")
    var originalWeight: Double = 0.0,

    @SerializedName("approxWeight")
    var approxWeight: Double = 0.0,

    @SerializedName("stock")
    var stock: Double = 0.0,

    var statusState: Boolean = false
): java.io.Serializable