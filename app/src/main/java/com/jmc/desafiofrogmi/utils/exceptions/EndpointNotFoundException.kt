package com.jmc.desafiofrogmi.utils.exceptions

private const val MESSAGE: String = "Endpoint call not found exception"

class EndpointNotFoundException(code: Int) : RepositoryException(code, MESSAGE)