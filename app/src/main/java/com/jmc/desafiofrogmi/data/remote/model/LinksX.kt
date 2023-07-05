package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("self")
    val self: String
)