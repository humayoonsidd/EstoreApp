package com.shoppingcart.estoreapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shoppingcart.estoreapp.data.converter.ProductTypeConverter
import com.shoppingcart.estoreapp.data.dao.CartItemDao
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product


@Database(entities = [Product::class, CartItem::class], version = 1)
@TypeConverters(ProductTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
}
