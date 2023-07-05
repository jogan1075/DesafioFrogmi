package com.jmc.desafiofrogmi.data.source

import com.jmc.desafiofrogmi.data.repository.Remote
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock

internal class SourceFactoryTest {

    private val remote = mock<Remote>()
    private val factory = SourceFactory(remote)

    @Test
    fun `when getCache, then Remote instance not null`() {
        Assert.assertNotNull(factory.getRemote())
    }
}