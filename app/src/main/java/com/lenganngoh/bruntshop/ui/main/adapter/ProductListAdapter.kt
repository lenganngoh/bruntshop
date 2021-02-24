package com.lenganngoh.bruntshop.ui.main.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lenganngoh.bruntshop.R
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.ViewholderProductListRowBinding
import kotlin.math.roundToInt

class ProductListAdapter(
    private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<CalendarDetailDetailListItemViewHolder>() {

    private val products = ArrayList<Product>()

    interface OnProductClickListener {
        fun onProductClicked(product: Product)
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarDetailDetailListItemViewHolder {
        val binding: ViewholderProductListRowBinding =
            ViewholderProductListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CalendarDetailDetailListItemViewHolder(binding, onProductClickListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CalendarDetailDetailListItemViewHolder, position: Int) =
        holder.bind(products[position])
}

class CalendarDetailDetailListItemViewHolder(
    private val binding: ViewholderProductListRowBinding,
    private val listener: ProductListAdapter.OnProductClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.txtCategory.text = product.category
        binding.txtName.text = product.name
        binding.txtPrice.text = String.format("%s%s", "$", product.price?.toDouble()?.roundToInt())

        setProductImage(product.id)
        setProductImageBackground(product.bgColor!!)

        binding.root.setOnClickListener {
            listener.onProductClicked(product)
        }
    }

    private fun setProductImage(id: String) {
        when (id) {
            "p_1" -> binding.imgProduct.setImageResource(R.drawable.ic_p_1)
            "p_2" -> binding.imgProduct.setImageResource(R.drawable.ic_p_2)
            "p_3" -> binding.imgProduct.setImageResource(R.drawable.ic_p_3)
            "p_4" -> binding.imgProduct.setImageResource(R.drawable.ic_p_4)
            "p_5" -> binding.imgProduct.setImageResource(R.drawable.ic_p_5)
        }
    }

    private fun setProductImageBackground(color: String) {
        val drawable = binding.bgProduct.background as GradientDrawable
        drawable.setColor(Color.parseColor(color))
        binding.bgProduct.background = drawable
    }
}