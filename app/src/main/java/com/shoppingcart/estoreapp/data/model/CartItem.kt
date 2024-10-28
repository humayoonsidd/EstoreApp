package com.shoppingcart.estoreapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.shoppingcart.estoreapp.data.converter.ProductTypeConverter

@Entity(tableName = "cart_items")
@TypeConverters(ProductTypeConverter::class)
data class CartItem(
    @PrimaryKey(autoGenerate = true) val cartItemId: Int = 0,
    val productId: Int,
    var quantity: Int,
    var product: Product?
)
