package com.jmc.desafiofrogmi.domain.repository

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse

interface Repository {

    suspend fun getStores(page: Int) : StoreResponse
}