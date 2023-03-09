package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName

class ChickenPreparation(

    @SerializedName("withSkinWeight") var withSkinWeight: ChickenPreparationWeight? = ChickenPreparationWeight(),
    @SerializedName("withOutSkinWeight") var withOutSkinWeight: ChickenPreparationWeight? = ChickenPreparationWeight(),
    @SerializedName("withSkinRoastedWeight") var withRoastedWeight: ChickenPreparationWeight? = ChickenPreparationWeight()

) : java.io.Serializable