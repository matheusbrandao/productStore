package com.matheusbrandao.productstore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheusbrandao.productstore.model.ProductList
import com.matheusbrandao.productstore.model.Result
import com.matheusbrandao.productstore.repository.ProductRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList = MutableLiveData<Result<ProductList>>()
    val productList: LiveData<Result<ProductList>>
        get() = _productList

    fun fetchProductList() {
        viewModelScope.launch {
            repository.fetchProductListFromRemote()
                .onStart {
                    _productList.postValue(Result.Loading)
                }
                .catch { throwable ->
                    _productList.postValue(Result.Error(throwable))
                }
                .collect { response ->
                    _productList.postValue(Result.Success(response))
                }
        }
    }
}
