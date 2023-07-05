package com.jmc.desafiofrogmi.data.remote.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("pagination")
    val pagination: Pagination
)