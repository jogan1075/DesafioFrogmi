package com.jmc.desafiofrogmi.domain

import com.jmc.desafiofrogmi.data.remote.model.StoreResponse
import com.jmc.desafiofrogmi.domain.repository.Repository
import com.jmc.desafiofrogmi.factory.TestFactory
import com.jmc.desafiofrogmi.utils.testing.RandomFactory
import com.jmc.desafiofrogmi.utils.testing.TestCoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

internal class GetStoresUseCaseTest{

    private val repository = mock<Repository>()

    private val usecase = GetStoresUseCase(repository)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private fun stubSuccess(
        page: Int,
        resultValue: StoreResponse
    ) = runTest {
        val repositoryMethod = repository.getStores(page)
        Mockito.`when`(repositoryMethod).thenReturn(resultValue)
    }


    @Test
    fun `given params random , when GetStoresUseCase, then return successful`() =
        testCoroutineRule.runBlockingTest {
            val page = RandomFactory.generateInt()
            val response = TestFactory.makeStoreResponse()

            stubSuccess(page, response)

            val testObserver = usecase.execute(page)


            assertEquals(testObserver, response)
            Mockito.verify(repository).getStores(page)
        }

}