package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class Relationships(
    @SerializedName("brands")
    val brands: Brands,
    @SerializedName("zones")
    val zones: Zones
)