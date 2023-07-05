package com.jmc.desafiofrogmi.data.repository

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import retrofit2.Response

interface Remote {

    suspend fun getStores(page: Int) : StoreResponse
}