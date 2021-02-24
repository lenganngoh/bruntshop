package com.lenganngoh.bruntshop.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll() : LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product): Long

    @Delete
    fun delete(product: Product)

    @Query("DELETE from product")
    fun clear()
}