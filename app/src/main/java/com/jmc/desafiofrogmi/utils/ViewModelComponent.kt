package com.jmc.desafiofrogmi.utils

interface ViewState {
    val isLoading: Boolean
    fun getErrorMessage(): String? = null
    fun clearErrors(): ViewState? = null
}

interface ViewEvent

interface ViewSideEffect