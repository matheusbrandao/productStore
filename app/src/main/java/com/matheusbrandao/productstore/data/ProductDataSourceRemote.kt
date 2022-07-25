package com.matheusbrandao.productstore.data

import com.matheusbrandao.productstore.model.ProductList
import com.matheusbrandao.productstore.remote.service.ProductService

class ProductDataSourceRemote(
    private val service: ProductService
) {

    suspend fun fetchProductList(): ProductList {
        return service.fetchProductList()
    }
}
