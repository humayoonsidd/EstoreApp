package com.shoppingcart.estoreapp.data.repository

import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductRepository {
    // Sample hardcoded product list
    private val productList = listOf(
        Product(1, "Smartphone", "A high-end smartphone", 699.99),
        Product(2, "Laptop", "Powerful gaming laptop", 1199.99),
        Product(3, "Headphones", "Noise-canceling headphones", 199.99)
    )
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun getProducts(): List<Product> {
        return productList
    }

    fun addToCart(product: Product) {
        val existingItem = _cartItems.value.find { it.product == product }
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            val updatedList = _cartItems.value.toMutableList()
            updatedList[_cartItems.value.indexOf(existingItem)] = updatedItem
            _cartItems.value = updatedList
        } else {
            val newItem = CartItem(product, 1)
            _cartItems.value += newItem
        }
    }

}