package com.example.hypex.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hypex.data.Product
import com.example.hypex.databinding.ProductRvItemBinding

class BestProductAdapter: RecyclerView.Adapter<BestProductAdapter.BestProductViewHolder>()  {
    inner class BestProductViewHolder(private val binding: ProductRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product : Product) {
            binding.apply {
                Glide.with(itemView).load(product.images[0]).into(imgProduct)
                product.offerPercentage?.let {
                    val remainingPricePercentage = 1f -it
                    val priceAfterOffer = remainingPricePercentage * product.price
                    tvNewPrice.text = "Ksh ${String.format("%.2f", priceAfterOffer)}"
                    tvPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                if(product.offerPercentage == null)
                    tvNewPrice.visibility = View.INVISIBLE
                tvPrice.text = "Ksh ${product.price}"
                tvName.text = product.name
            }
        }
    }
    private val diffCallBack = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestProductViewHolder {
        return BestProductViewHolder(
            ProductRvItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }
    override fun onBindViewHolder(holder: BestProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}