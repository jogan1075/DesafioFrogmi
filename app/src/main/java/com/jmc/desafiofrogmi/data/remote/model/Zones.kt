package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class Zones(
    @SerializedName("data")
    val `data`: DataX
)