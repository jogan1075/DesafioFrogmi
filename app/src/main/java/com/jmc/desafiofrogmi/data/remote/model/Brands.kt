package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class Brands(
    @SerializedName("data")
    val `data`: DataX
)