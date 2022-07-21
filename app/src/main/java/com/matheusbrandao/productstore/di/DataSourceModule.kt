package com.matheusbrandao.productstore.di

import com.matheusbrandao.productstore.data.ProductDataSourceRemote
import org.koin.dsl.module

val dataSourceModule = module {

    factory {
        ProductDataSourceRemote(get())
    }
}
