package com.matheusbrandao.productstore.repository

import com.matheusbrandao.productstore.data.ProductDataSourceRemote
import com.matheusbrandao.productstore.model.ProductList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepository(
    private val dataSourceRemote: ProductDataSourceRemote,
    private val dispatcher: CoroutineDispatcher
) {

    fun fetchProductListFromRemote(): Flow<ProductList> = flow {
        val response = dataSourceRemote.fetchProductList()
        emit(response)
    }.flowOn(dispatcher)
}
