package com.lenganngoh.bruntshop.ui.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lenganngoh.bruntshop.R
import com.lenganngoh.bruntshop.data.DatabaseManager
import com.lenganngoh.bruntshop.util.JSONHelper
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.databinding.ActivityMainBinding
import com.lenganngoh.bruntshop.ui.main.fragment.CartFragment
import com.lenganngoh.bruntshop.ui.main.fragment.CheckoutFragment
import com.lenganngoh.bruntshop.ui.main.fragment.ConfirmationFragment
import com.lenganngoh.bruntshop.ui.main.fragment.ProductListFragment
import com.lenganngoh.bruntshop.util.ActivityUtil
import com.lenganngoh.bruntshop.util.AnimationUtil
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var handler: Handler

    private var productListFragment = ProductListFragment.newInstance()
    private var cartFragment = CartFragment.newInstance()
    private var checkoutFragment = CheckoutFragment.newInstance()
    private var confirmationFragment = ConfirmationFragment.newInstance()

    private val cachedProducts = ArrayList<Product>()
    private val cachedCart = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DatabaseManager.initDatabase(this)

        setupActivityProp()
        initViewModel()
        setupObserver()

        initializeViews()
        openFragment(productListFragment)
    }

    private fun setupActivityProp() {
        ActivityUtil.renderFullScreen(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityUtil.setStatusBarTextColor(this, false)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getAll().observe(this, {
            cachedCart.clear()
            cachedCart.addAll(it)

            if (it.isNotEmpty()) {
                binding.txtCartCount.visibility = View.VISIBLE
                binding.txtCartCount.text = it.size.toString()
            } else {
                binding.txtCartCount.visibility = View.GONE
            }
            cartFragment.setProducts(it)
        })
    }

    private fun initializeViews() {
        handler = Handler(mainLooper)

        binding.iconCart.setOnClickListener {
            if (supportFragmentManager.findFragmentById(R.id.contFragment) == cartFragment) {
                openFragment(productListFragment)
            } else {
                openFragment(cartFragment)
            }
        }
    }

    private fun initializeProductsList() {
        val json = JSONHelper.fetchProductList(this)
        if (!json.isNullOrEmpty()) {
            val jsonObject = JSONObject(json)
            val products =
                JSONHelper.parseJsonToProductList(jsonObject.getJSONArray("products").toString())
            initializeRecyclerView(products)
        }
    }

    private fun initializeRecyclerView(products: List<Product>) {
        cachedProducts.clear()
        cachedProducts.addAll(products)
        productListFragment.setProducts(products)
    }

    private fun openFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.contFragment) != fragment) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .replace(R.id.contFragment, fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
        }

        when (fragment) {
            is ProductListFragment -> {
                val listener = object : ProductListFragment.Listener {
                    override fun onProductClicked(product: Product) {
                        viewModel.insert(product)
                        showBottomDialog(product)
                    }
                }
                productListFragment.setListener(listener)
                initializeProductsList()
            }
            is CartFragment -> {
                val listener = object : CartFragment.Listener {
                    override fun onProductDeleted(product: Product) {
                        viewModel.delete(product)
                    }

                    override fun onBuyNow() {
                        openFragment(checkoutFragment)
                    }

                    override fun onAddProduct() {
                        openFragment(productListFragment)
                    }
                }
                cartFragment.setListener(listener)
            }
            is CheckoutFragment -> {
                val listener = object : CheckoutFragment.Listener {
                    override fun onPay() {
                        viewModel.clear()
                        openFragment(confirmationFragment)
                    }
                }
                checkoutFragment.setListener(listener)
                checkoutFragment.setProducts(cachedCart)
            }

            is ConfirmationFragment -> {
                val listener = object : ConfirmationFragment.Listener {
                    override fun onReturn() {
                        openFragment(productListFragment)
                    }
                }
                confirmationFragment.setListener(listener)
            }
        }
    }

    private fun showBottomDialog(product: Product) {
        binding.viewBottomNotification.setProduct(product)

        binding.viewBottomNotification.visibility = View.VISIBLE
        val animationIn = AnimationUtil.inFromBottomAnimation()
        animationIn.setAnimationListener(null)
        binding.viewBottomNotification.startAnimation(animationIn)

        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            val animationOut = AnimationUtil.outToBottomAnimation()
            animationOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    binding.viewBottomNotification.visibility = View.INVISIBLE
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })
            binding.viewBottomNotification.visibility = View.VISIBLE
            binding.viewBottomNotification.startAnimation(animationOut)
        }, 1000)
    }
}