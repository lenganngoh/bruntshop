package com.lenganngoh.bruntshop.util

import android.content.Context
import com.google.gson.Gson
import com.lenganngoh.bruntshop.data.Product
import java.io.IOException
import java.nio.charset.Charset

class JSONHelper {
    companion object {
        val gson = Gson()

        fun fetchProductList(context: Context): String? {
            return try {
                val inputStream = context.assets.open("product_list")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()

                buffer.toString(Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                null
            }
        }

        fun parseJsonToProductList(json: String): List<Product> {
            return gson.fromJson(json, Array<Product>::class.java).toList()
        }
    }
}