package com.jmc.desafiofrogmi.utils


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


abstract class BaseVM<Intent : ViewEvent, uiState : ViewState>(
    val stateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var initialData: Any

    private val initialState: uiState by lazy { setInitialState() }

    private val _viewState: MutableState<uiState> = mutableStateOf(initialState)

    val viewState: State<uiState> = _viewState

    val state get() = _viewState.value

    private val _event: MutableSharedFlow<Intent> = MutableSharedFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(intent: Intent) {
        viewModelScope.launch { _event.emit(intent) }
    }

    protected fun setState(reducer: uiState.() -> uiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    protected abstract fun setInitialState(): uiState

    protected abstract fun handleEvents(intent: Intent)

}
