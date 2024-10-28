package com.shoppingcart.estoreapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shoppingcart.estoreapp.data.model.Product

class ProductTypeConverter {
    @TypeConverter
    fun fromProduct(product: Product?): String? {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun toProduct(productString: String?): Product? {
        return Gson().fromJson(productString, Product::class.java)
    }
}