package com.iitms.bfr.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SearchProduct(
    @SerializedName("productList")
    var productList : List<SubCategoryDetail>? = null
): Serializable