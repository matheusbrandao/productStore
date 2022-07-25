package com.matheusbrandao.productstore.repository

import com.matheusbrandao.productstore.data.ProductDataSourceRemote
import com.matheusbrandao.productstore.utils.MainDispatcherRule
import com.matheusbrandao.productstore.utils.factory.DataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var dataSourceRemote: ProductDataSourceRemote

    private val repository: ProductRepository by lazy {
        ProductRepository(
            dataSourceRemote = dataSourceRemote,
            dispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `should emit product list when request is successful`() {
        val fakeProductList = DataFactory.fakeProductList()

        coEvery {
            dataSourceRemote.fetchProductList()
        } returns fakeProductList

        runTest(StandardTestDispatcher()) {
            val state = repository.fetchProductList()
            val response = state.first()

            assertEquals(response, fakeProductList)
        }
    }
}
