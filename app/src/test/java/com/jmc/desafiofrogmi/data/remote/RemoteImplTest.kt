package com.jmc.desafiofrogmi.data.remote

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.data.remote.service.ApiService
import com.jmc.desafiofrogmi.factory.TestFactory
import com.jmc.desafiofrogmi.utils.testing.RandomFactory
import com.jmc.desafiofrogmi.utils.testing.TestCoroutineRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RemoteImplTest {

    private val restApi = mock<ApiService>()

    private val remoteImpl = RemoteImpl(restApi)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private suspend fun stubRestApi(
        page: Int,
        resultValue: StoreResponse
    ) {
        whenever(
            restApi.getStores(
                page = page,
            )
        ).thenReturn(resultValue)
    }


    @Test
    fun `given makeStoreResponse, when getStores, then Completes`() = testCoroutineRule.runBlockingTest {
        val page = RandomFactory.generateInt()
        val response =  TestFactory.makeStoreResponse()

        stubRestApi(page, response)

        val testObserver = remoteImpl.getStores(page)

        assertEquals(response, testObserver)
    }
}