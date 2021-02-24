package com.lenganngoh.bruntshop.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.FragmentCheckoutBinding
import com.lenganngoh.bruntshop.ui.main.adapter.ProductListAdapter
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.roundToInt

class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var listener: Listener

    private var adapter: ProductListAdapter? = null
    private val products = ArrayList<Product>()

    companion object {
        fun newInstance(): CheckoutFragment {
            val args = Bundle()
            val fragment = CheckoutFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCheckoutBinding.inflate(LayoutInflater.from(context), container, false)

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
        computeTotal()

        binding.txtPayment.setOnClickListener {
            if (!binding.etName.text.isNullOrEmpty() && isEmailValid(binding.etEmail.text.toString()) && binding.swTerms.isChecked) {
                binding.etName.setText("")
                binding.etEmail.setText("")
                binding.swTerms.isChecked = false

                listener.onPay()
            }
        }
    }

    private fun computeTotal() {
        var total = 0.0
        products.forEach {
            if (!it.price.isNullOrEmpty()) {
                total += it.price.toDouble()
            }
        }
        binding.txtPayment.text = String.format("Pay %s%s", "$", total.roundToInt())
    }

    private fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }


    fun setListener(listener: Listener) {
        this.listener = listener
    }

    interface Listener {
        fun onPay()
    }
}