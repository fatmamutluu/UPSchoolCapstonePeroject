package com.example.upshoolcapstoneproject.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upshoolcapstoneproject.data.model.response.Product
import com.example.upshoolcapstoneproject.data.model.response.ProductListUI
import com.example.upshoolcapstoneproject.databinding.ItemProductBinding


class ProductsAdapter(
    private val onProductClick: (Int) -> Unit
) : ListAdapter<ProductListUI, ProductsAdapter.ProductViewHolder>(ProductDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onProductClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(getItem(position))

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onProductClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductListUI) {
            with(binding) {
                tvTitle.text = product.title
                tvPrice.text = "${product.price} ₺"
                tvSalePrice.text = "${product.salePrice} ₺"
                tvSalePrice.text = "${product.salePrice} ₺"
                tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                Glide.with(ivProduct).load(product.imageOne).into(ivProduct)

                root.setOnClickListener {
                    onProductClick(product.id)
                }
            }
        }
    }

    class ProductDiffUtilCallBack : DiffUtil.ItemCallback<ProductListUI>() {
        override fun areItemsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductListUI, newItem: ProductListUI): Boolean {
            return oldItem == newItem
        }
    }
}