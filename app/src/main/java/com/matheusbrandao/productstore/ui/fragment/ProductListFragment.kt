package com.matheusbrandao.productstore.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.matheusbrandao.productstore.R
import com.matheusbrandao.productstore.databinding.FragmentProductListBinding
import com.matheusbrandao.productstore.viewModel.ProductListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
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

        viewModel.fetchProductList()
    }
}
