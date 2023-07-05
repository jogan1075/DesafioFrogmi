package com.jmc.desafiofrogmi.data.source

import com.jmc.desafiofrogmi.data.repository.Remote
import javax.inject.Inject

internal class SourceFactory @Inject constructor(
    private val remote: Remote,
) {
    fun getRemote(): Remote = remote

}