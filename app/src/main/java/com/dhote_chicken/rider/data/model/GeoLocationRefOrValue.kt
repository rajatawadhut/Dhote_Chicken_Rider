package com.dhote_chicken.rider.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GeoLocationRefOrValue (
    @SerializedName("geometry" )
    var geometry : Geometry? = null
): Serializable