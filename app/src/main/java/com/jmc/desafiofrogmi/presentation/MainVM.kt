package com.jmc.desafiofrogmi.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jmc.desafiofrogmi.data.remote.model.Data
import com.jmc.desafiofrogmi.domain.GetStoresUseCase
import com.jmc.desafiofrogmi.presentation.contract.MainContract
import com.jmc.desafiofrogmi.utils.BaseVM
import com.jmc.desafiofrogmi.utils.errors.DomainError
import com.jmc.desafiofrogmi.utils.exceptions.RepositoryCoroutineHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    stateHandle: SavedStateHandle,
    private val useCase: GetStoresUseCase
) :
    BaseVM<MainContract.Event, MainContract.State>(stateHandle) {

    private var pList = listOf<Data>()

    init {
        setState { copy(isLoading = true).clearErrors() }
        getStores(1)
    }


    override fun setInitialState(): MainContract.State {
        return MainContract.State()
    }

    override fun handleEvents(intent: MainContract.Event) {
        when (intent) {
            is MainContract.Event.GetListPokemon -> {
                showNextPage(intent.pageNext, intent.pagePrev)
            }
        }
    }

    private fun getStores(page: Int) {
        launch {
            val resp = useCase.execute(page)
            if (resp.data.isNotEmpty()) {
                pList = pList.mergeWith(resp.data)
                val split = resp.links.next.split("&")
                val split2 = resp.links.prev?.split("&")
                val nextPage = split[1].substring(5)
                val prevPage = split2?.get(1)?.substring(5)
                setState {
                    copy(
                        isLoading = false,
                        list = pList,
                        positionNext = nextPage.toInt(),
                        positionOld = if (prevPage.isNullOrEmpty()) 0 else prevPage.toInt()
                    )
                }
            }
        }
    }

    private fun showNextPage(next: Int, prev: Int?) {

        if (prev != null && next >= prev) {
            getStores(next)
        }
    }

    private fun launch(invoke: suspend () -> Unit) {
        viewModelScope.launch(RepositoryCoroutineHandler(::handleError)) {
            invoke()
        }
    }

    private fun handleError(domainError: DomainError) {}


    fun <Data> List<Data>.mergeWith(second: List<Data>): List<Data> {
        val list = ArrayList(this)
        list.addAll(second)
        return list
    }
}