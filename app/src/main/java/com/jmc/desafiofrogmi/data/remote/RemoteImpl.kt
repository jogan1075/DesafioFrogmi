package com.jmc.desafiofrogmi.data.remote

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.data.remote.service.ApiService
import com.jmc.desafiofrogmi.data.repository.Remote
import retrofit2.Response
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val apiService: ApiService) : Remote {

    override suspend fun getStores(page: Int): StoreResponse {
        return apiService.getStores(page)
    }
}