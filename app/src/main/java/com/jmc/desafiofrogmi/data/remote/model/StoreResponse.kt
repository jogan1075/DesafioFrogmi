package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("links")
    val links: LinksX,
    @SerializedName("meta")
    val meta: Meta
)