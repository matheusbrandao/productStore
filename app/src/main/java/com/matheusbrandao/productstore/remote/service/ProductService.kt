package com.matheusbrandao.productstore.remote.service

import retrofit2.http.GET

interface ProductService {

    @GET("")
    suspend fun fetchProductList()
}
