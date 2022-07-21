package com.matheusbrandao.productstore.remote.service

import com.matheusbrandao.productstore.model.ProductList
import retrofit2.http.GET

interface ProductService {

    @GET("mobile-assignment/search")
    suspend fun fetchProductList(): ProductList
}
