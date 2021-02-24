package com.lenganngoh.bruntshop.ui.main

import androidx.lifecycle.LiveData
import com.lenganngoh.bruntshop.data.AppDatabase
import com.lenganngoh.bruntshop.data.DatabaseManager
import com.lenganngoh.bruntshop.data.Product
import com.lenganngoh.bruntshop.data.ProductDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository {

    private var productDao: ProductDao
    private var products: LiveData<List<Product>>

    init {
        val db: AppDatabase? = DatabaseManager.getDatabase()

        productDao = db?.productDao()!!
        products = productDao.getAll()
    }

    fun insert(product: Product) {
        GlobalScope.launch {
            productDao.insert(product)
        }
    }

    fun delete(product: Product) {
        GlobalScope.launch {
            productDao.delete(product)
        }
    }

    fun clear() {
        GlobalScope.launch {
            productDao.clear()
        }
    }

    fun getAll(): LiveData<List<Product>> {
        return products
    }
}