package com.jmc.desafiofrogmi.utils.exceptions

private const val MESSAGE: String = "Session expired"
private const val CODE = 401

object SessionExpiredException : RepositoryException(CODE, MESSAGE)