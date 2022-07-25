package com.matheusbrandao.productstore.di

import com.matheusbrandao.productstore.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        ProductRepository(
            dataSourceRemote = get(),
            dispatcher = Dispatchers.IO
        )
    }
}
