package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("code")
    val code: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("full_address")
    val fullAddress: String,
    @SerializedName("name")
    val name: String
)