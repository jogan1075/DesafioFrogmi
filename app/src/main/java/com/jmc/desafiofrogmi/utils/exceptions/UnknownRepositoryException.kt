package com.jmc.desafiofrogmi.utils.exceptions

private const val CODE = -1
private const val MESSAGE: String = "Unknown error in repository network call"

object UnknownRepositoryException : RepositoryException(CODE, MESSAGE)