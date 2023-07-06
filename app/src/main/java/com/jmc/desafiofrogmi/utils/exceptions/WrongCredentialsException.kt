package com.jmc.desafiofrogmi.utils.exceptions

private const val MESSAGE: String = "Bad Credentials"
private const val CODE = 401

object WrongCredentialsException : RepositoryException(CODE, MESSAGE)