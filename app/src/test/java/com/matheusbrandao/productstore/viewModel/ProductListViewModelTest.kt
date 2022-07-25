package com.matheusbrandao.productstore.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matheusbrandao.productstore.model.ProductList
import com.matheusbrandao.productstore.model.Result
import com.matheusbrandao.productstore.repository.ProductRepository
import com.matheusbrandao.productstore.utils.MainDispatcherRule
import com.matheusbrandao.productstore.utils.factory.DataFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    private lateinit var channel: Channel<ProductList>

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMockKs
    lateinit var viewModel: ProductListViewModel

    @MockK
    lateinit var repository: ProductRepository

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = ProductListViewModel(repository)
        coEvery { repository.fetchProductList() } returns createFlowDefault()
    }

    @Test
    fun `should emit loading and error when request is unsuccessfully`() {
        val throwableFake = DataFactory.fakeThrowable()

        runTest(dispatcher) {
            viewModel.fetchProductList()

            assertEquals(Result.Loading, viewModel.productList.value)

            launch {
                channel.close(throwableFake)
            }

            assertEquals(Result.Error(throwableFake), viewModel.productList.value)
        }
    }

    @Test
    fun `should emit loading and success with product list when request is successfully`() {
        val fakeResponse: ProductList = DataFactory.fakeProductList()

        runTest(dispatcher) {
            viewModel.fetchProductList()

            assertEquals(Result.Loading, viewModel.productList.value)

            launch {
                channel.send(fakeResponse)
            }

            assertEquals(Result.Success(fakeResponse), viewModel.productList.value)
        }
    }

    private fun createFlowDefault(): Flow<ProductList> {
        channel = Channel()
        return channel.consumeAsFlow()
    }
}
