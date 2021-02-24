package com.lenganngoh.bruntshop.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lenganngoh.bruntshop.data.Product

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: MainRepository = MainRepository()
    private var products: LiveData<List<Product>> = repository.getAll()

    fun insert(product: Product) {
        return repository.insert(product)
    }

    fun delete(product: Product) {
        return repository.delete(product)
    }

    fun clear() = repository.clear()

    fun getAll() : LiveData<List<Product>> {
        return products
    }
}