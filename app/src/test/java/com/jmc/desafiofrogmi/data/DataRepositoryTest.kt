package com.jmc.desafiofrogmi.data

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.data.repository.Remote
import com.jmc.desafiofrogmi.data.source.SourceFactory
import com.jmc.desafiofrogmi.factory.TestFactory
import com.jmc.desafiofrogmi.utils.testing.RandomFactory
import com.jmc.desafiofrogmi.utils.testing.TestCoroutineRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class DataRepositoryTest{

    private val remote = mock<Remote>()

    private val factory = SourceFactory(remote)

    private val dataRepository = DataRepository(factory)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private fun stub(
        page: Int,
        resultValue: StoreResponse,
    ) = testCoroutineRule.runBlockingTest {
        whenever(
            remote.getStores(
                page = page
            )
        ).thenReturn(resultValue)
    }

    @Test
    fun `given data random, when getStores success, then Go to stateSuccessful`() =
        testCoroutineRule.runBlockingTest {

            val page = RandomFactory.generateInt()
            val response = TestFactory.makeStoreResponse()


            stub( page, response)

            val testObserver = dataRepository.getStores(page)

            assertEquals(testObserver, response)
            verify(remote).getStores(page = page)
        }
}