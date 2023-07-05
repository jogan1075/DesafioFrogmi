package com.jmc.desafiofrogmi.domain

import com.jmc.desafiofrogmi.domain.repository.Repository
import javax.inject.Inject

class GetStoresUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(page: Int) = repository.getStores(page)
}