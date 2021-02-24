package com.lenganngoh.bruntshop.ui.main.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.ViewholderCartListRowBinding
import com.lenganngoh.bruntshop.util.AnimationUtil
import kotlin.math.roundToInt

class CartListAdapter(
    private val onProductDeletedListener: OnProductDeletedListener
) : RecyclerView.Adapter<CartListViewHolder>() {

    private val products = ArrayList<Product>()

    interface OnProductDeletedListener {
        fun onProductDeleted(product: Product)
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartListViewHolder {
        val binding: ViewholderCartListRowBinding =
            ViewholderCartListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CartListViewHolder(binding, onProductDeletedListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) =
        holder.bind(products[position])
}

class CartListViewHolder(
    private val binding: ViewholderCartListRowBinding,
    private val listener: CartListAdapter.OnProductDeletedListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Product) {
        binding.txtProductName.text = product.name
        binding.txtProductPrice.text = String.format("%s%s", "$", product.price?.toDouble()?.roundToInt())
        setProductImageBackground(product.bgColor!!)

        binding.root.setOnClickListener {
//            val animateOut = AnimationUtil.outToRightAnimation()
//            animateOut.setAnimationListener(object : Animation.AnimationListener {
//                override fun onAnimationStart(p0: Animation?) {}
//
//                override fun onAnimationEnd(p0: Animation?) {
                    listener.onProductDeleted(product)
//                }
//
//                override fun onAnimationRepeat(p0: Animation?) {}
//
//            })
//            binding.root.startAnimation(animateOut)
        }
    }

    private fun setProductImageBackground(color: String) {
        val drawable = binding.bgProduct.background as GradientDrawable
        drawable.setColor(Color.parseColor(color))
        binding.bgProduct.background = drawable
    }
}