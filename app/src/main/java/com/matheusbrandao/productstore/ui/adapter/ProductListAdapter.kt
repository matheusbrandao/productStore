package com.matheusbrandao.productstore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matheusbrandao.productstore.databinding.ItemProductListBinding
import com.matheusbrandao.productstore.model.ProductList

class ProductListAdapter(
    private var list: List<ProductList.ProductItem> = emptyList()
) : RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemViewHolder {
        val binding = ItemProductListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductItemViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<ProductList.ProductItem>) {
        list = newList
        notifyDataSetChanged()
    }

    class ProductItemViewHolder(
        private val binding: ItemProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductList.ProductItem) {
            with(binding) {
                Glide.with(imageViewProductPhoto.context)
                    .load(item.productImage)
                    .into(imageViewProductPhoto)

                textViewProductTitle.text = item.productName
            }
        }
    }
}
