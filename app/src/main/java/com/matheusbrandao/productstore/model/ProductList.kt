package com.matheusbrandao.productstore.model

data class ProductList(
    var products: List<ProductItem>
) {

    data class ProductItem(
        var productId: Long,
        var productName: String,
        var productImage: String
    )
}
