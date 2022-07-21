package com.matheusbrandao.productstore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.matheusbrandao.productstore.R
import com.matheusbrandao.productstore.databinding.FragmentProductListBinding
import com.matheusbrandao.productstore.model.ProductList
import com.matheusbrandao.productstore.model.Result
import com.matheusbrandao.productstore.ui.adapter.ProductListAdapter
import com.matheusbrandao.productstore.viewModel.ProductListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var adapter: ProductListAdapter
    private val viewModel by viewModel<ProductListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_product_list, container, false)
        binding = FragmentProductListBinding.bind(root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()
        setupObservable()
        viewModel.fetchProductList()
    }

    private fun setupListAdapter() {
        adapter = ProductListAdapter()

        with(binding.recyclerViewData) {
            adapter = this@ProductListFragment.adapter
            layoutManager = GridLayoutManager(
                context,
                COLUMNS_IN_LIST,
                GridLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            addItemDecoration(DividerItemDecoration(context, LinearLayout.HORIZONTAL))
        }
    }

    private fun setupObservable() {
        viewModel.productList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Result.Error -> handleWithViews(errorVisible = true)
                Result.Loading -> handleWithViews(loadingVisible = true)
                is Result.Success -> {
                    handleWithProductListResult(state.data)
                    handleWithViews(dataVisible = true)
                }
            }
        }
    }

    private fun handleWithProductListResult(products: ProductList) {
        adapter.updateList(products.products)
    }

    private fun handleWithViews(
        loadingVisible: Boolean = false,
        errorVisible: Boolean = false,
        dataVisible: Boolean = false
    ) {
        binding.progressBarLoading.visibility = if (loadingVisible) View.VISIBLE else View.GONE
        binding.textViewError.visibility = if (errorVisible) View.VISIBLE else View.GONE
        binding.recyclerViewData.visibility = if (dataVisible) View.VISIBLE else View.GONE
    }

    companion object {
        private const val COLUMNS_IN_LIST = 2
    }
}
