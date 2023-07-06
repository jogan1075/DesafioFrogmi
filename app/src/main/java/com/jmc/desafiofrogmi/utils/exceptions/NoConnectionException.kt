package com.jmc.desafiofrogmi.utils.exceptions

private const val MESSAGE: String = "Network connection is not available"
private const val CODE = 0

object NoConnectionException : RepositoryException(CODE, MESSAGE)