package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class OrderLifeCycleStatus(
    @SerializedName("status") var status: String? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null,
    @SerializedName("createdOn") var createdOn: String? = null,
    @SerializedName("createdBy") var createdBy: String? = null,
    @SerializedName("lastUpdatedBy") var lastUpdatedBy: String? = null
) : java.io.Serializable