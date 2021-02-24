package com.lenganngoh.bruntshop.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.FragmentCartBinding
import com.lenganngoh.bruntshop.ui.main.adapter.CartListAdapter
import java.lang.Exception
import kotlin.math.roundToInt

class CartFragment : Fragment(), CartListAdapter.OnProductDeletedListener {
    private lateinit var binding: FragmentCartBinding
    private lateinit var listener: Listener

    private var adapter: CartListAdapter? = null
    private val products = ArrayList<Product>()

    companion object {
        fun newInstance(): CartFragment {
            val args = Bundle()
            val fragment = CartFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(LayoutInflater.from(context), container, false)

        initializeViews()

        return binding.root
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)

        try {
            adapter!!.setProducts(products)
            computeTotal()

        } catch (e: Exception) {
        }

        initEmptyContainer()
    }

    private fun initializeViews() {
        binding.btnBuyNow.setOnClickListener {
            if (products.isNotEmpty()) {
                listener.onBuyNow()
            }
        }

        binding.iconEmptyCart.setOnClickListener {
            listener.onAddProduct()
        }

        adapter = CartListAdapter(this)
        binding.rvProducts.layoutManager = LinearLayoutManager(activity)
        binding.rvProducts.adapter = adapter

        adapter!!.setProducts(products)

        computeTotal()
        initEmptyContainer()
    }

    private fun computeTotal() {
        var total = 0.0
        products.forEach {
            if (!it.price.isNullOrEmpty()) {
                total += it.price.toDouble()
            }
        }
        binding.txtPrice.text = String.format("%s%s", "$", total.roundToInt())
    }

    private fun initEmptyContainer() {
        try {
            binding.contEmpty.visibility = if (products.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        } catch (e: Exception) {}
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onProductDeleted(product: Product) {
        listener.onProductDeleted(product)
    }

    interface Listener {
        fun onProductDeleted(product: Product)
        fun onBuyNow()
        fun onAddProduct()
    }
}