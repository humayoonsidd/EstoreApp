package com.shoppingcart.estoreapp.data.dao

import androidx.room.*
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getCartItemByProductId(productId: Int): CartItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Update
    suspend fun update(cartItem: CartItem)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItem>

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)
}
