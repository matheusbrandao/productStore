package com.matheusbrandao.productstore.di

import com.matheusbrandao.productstore.viewModel.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        ProductListViewModel(
            repository = get()
        )
    }
}
