package com.lenganngoh.bruntshop.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.FragmentProductListBinding
import com.lenganngoh.bruntshop.ui.main.adapter.ProductListAdapter
import java.lang.Exception

class ProductListFragment : Fragment(), ProductListAdapter.OnProductClickListener {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var listener: Listener

    private var adapter: ProductListAdapter? = null
    private val products = ArrayList<Product>()

    companion object {
        fun newInstance(): ProductListFragment {
            val args = Bundle()
            val fragment = ProductListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(LayoutInflater.from(context), container, false)

        initializeViews()

        return binding.root
    }


    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)

        try {
            adapter!!.setProducts(products)
        } catch (e: Exception) {
        }
    }

    private fun initializeViews() {
        adapter = ProductListAdapter(this)
        binding.rvProducts.layoutManager = LinearLayoutManager(activity)
        binding.rvProducts.adapter = adapter

        adapter!!.setProducts(products)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onProductClicked(product: Product) {
        listener.onProductClicked(product)
    }

    interface Listener {
        fun onProductClicked(product: Product)
    }
}