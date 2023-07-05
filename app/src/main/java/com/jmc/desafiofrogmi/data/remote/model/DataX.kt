package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)