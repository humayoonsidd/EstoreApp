package com.shoppingcart.estoreapp.data.repository

import com.shoppingcart.estoreapp.data.database.AppDatabase
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * ProductRepository is responsible for managing product data and
 * cart items within the application. It interacts with the Room
 * database to perform CRUD (Create, Read, Update, Delete) operations
 * on products and cart items, facilitating data persistence and
 * retrieval.
 *
 * Key functionalities of this repository include:
 *
 * - Loading initial products into the database and exposing them
 *   as a StateFlow for observing product changes.
 * - Managing cart items, including adding products to the cart,
 *   updating their quantities, and clearing the cart.
 * - Providing methods to retrieve products and cart items from
 *   the database.
 *
 * The repository employs Kotlin coroutines for asynchronous operations
 * and ensures that database interactions are performed on the IO
 * dispatcher to avoid blocking the main thread.
 */
class ProductRepository @Inject constructor(
    private val database: AppDatabase
) {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        // You can call a coroutine to insert the products in the database
        // Use a Coroutine to perform database operations
        insertInitialProducts()
    }

    private fun insertInitialProducts() {
        // Load products from the Room database
        val productList = listOf(
            Product(1, "Smartphone", "A high-end smartphone", 699.99),
            Product(2, "Laptop", "Powerful gaming laptop", 1199.99),
            Product(3, "Headphones", "Noise-canceling headphones", 199.99),
            Product(4, "Smartwatch", "Fitness smartwatch", 299.99),
            Product(5, "Tablet", "Large screen tablet", 499.99),
            Product(6, "Camera", "Professional camera", 799.99),
            Product(7, "Drone", "High-definition drone", 999.99)
        )
        _products.value = productList

        // Launching a coroutine to insert products
        CoroutineScope(Dispatchers.IO).launch {
            database.cartItemDao().insertAll(productList)
            // Update the state with loaded products
            _products.value = productList
        }
    }

    suspend fun addToCart(product: Product) {
        withContext(Dispatchers.IO) {
            val existingItem = database.cartItemDao().getCartItemByProductId(product.id)
            if (existingItem != null) {
                existingItem.quantity++
                database.cartItemDao().update(existingItem)
            } else {
                val newCartItem = CartItem(productId = product.id, quantity = 1, product = product)
                database.cartItemDao().insert(newCartItem)
            }
            _cartItems.value = database.cartItemDao().getAllCartItems()
        }
    }

    suspend fun clearCart() {
        withContext(Dispatchers.IO) {
            database.cartItemDao().clearCart()
            _cartItems.value = emptyList()
        }
    }

    suspend fun getProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            database.cartItemDao().getAllProducts()
        }
    }

    suspend fun getCartItems(): List<CartItem> {
        return withContext(Dispatchers.IO) {
            database.cartItemDao().getAllCartItems()
        }
    }
}
