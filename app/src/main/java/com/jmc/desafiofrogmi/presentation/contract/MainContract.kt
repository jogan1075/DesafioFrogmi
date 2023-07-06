package com.jmc.desafiofrogmi.presentation.contract

import com.jmc.desafiofrogmi.data.remote.model.Data
import com.jmc.desafiofrogmi.utils.ViewEvent
import com.jmc.desafiofrogmi.utils.ViewState

class MainContract {

    sealed class Event : ViewEvent {
        data class GetListStores(val pageNext: Int,val pagePrev: Int?) : Event()
    }

    data class State(
        override val isLoading: Boolean = false,
        val list: List<Data> = emptyList(),
        val positionNext: Int = 0,
        val positionOld: Int? = 0,
        val error: String? = null
    ) : ViewState {

        override fun getErrorMessage() = error

        override fun clearErrors(): State {
            return copy(error = null, list = emptyList())
        }
    }
}