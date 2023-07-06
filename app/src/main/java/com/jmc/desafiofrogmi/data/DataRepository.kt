package com.jmc.desafiofrogmi.data


import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.data.source.SourceFactory
import com.jmc.desafiofrogmi.domain.repository.Repository
import com.jmc.desafiofrogmi.utils.base.BaseRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class DataRepository @Inject constructor(
    private val factory: SourceFactory
) : Repository , BaseRepository() {

    override suspend fun getStores(page: Int): StoreResponse {
//        return coroutineScope {
//            async {
//                factory.getRemote().getStores(page)
//            }.await()
//        }

        return safe {
            factory.getRemote().getStores(page)
        }
    }
}

