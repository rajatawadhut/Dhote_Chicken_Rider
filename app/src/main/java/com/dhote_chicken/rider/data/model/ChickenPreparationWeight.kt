package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName

class ChickenPreparationWeight(
    @SerializedName("additionalAmount") var additionalAmount: Int = 0,
    @SerializedName("additionalAmountFlag") var additionalAmountFlag: Boolean = false,
    @SerializedName("pieceSize") var pieceSize: String? = null,
    @SerializedName("weight") var weight: Int = 0

) : java.io.Serializable