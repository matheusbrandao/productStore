package com.matheusbrandao.productstore.utils.factory

import com.matheusbrandao.productstore.model.ProductList
import kotlin.random.Random

object DataFactory {

    private fun fakeLong() = Random.nextLong()

    private fun fakeString() = fakeLong().toString()

    fun fakeThrowable() = Throwable(fakeString())

    fun fakeProductList() = ProductList(
        products = arrayListOf(
            ProductList.ProductItem(
                productId = fakeLong(),
                productName = fakeString(),
                productImage = fakeString()
            ),
            ProductList.ProductItem(
                productId = fakeLong(),
                productName = fakeString(),
                productImage = fakeString()
            )
        )
    )
}
