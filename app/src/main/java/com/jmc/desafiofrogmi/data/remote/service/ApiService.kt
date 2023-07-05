package com.jmc.desafiofrogmi.data.remote.service

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("stores?per_page=15")
    suspend fun getStores(
        @Query("page") page: Int
    ): StoreResponse
}