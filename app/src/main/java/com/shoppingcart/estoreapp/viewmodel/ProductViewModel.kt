package com.shoppingcart.estoreapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product
import com.shoppingcart.estoreapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> get() = _cartItems

    init {
        viewModelScope.launch {
            repository.products
            _products.value = repository.getProducts()
            _cartItems.value = repository.getCartItems()
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            repository.addToCart(product)
            _cartItems.value = repository.getCartItems() // Update cart items
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            _cartItems.value = repository.getCartItems() // Update cart items
        }
    }

    fun getProductById(productId: Int): Product? {
        return _products.value.find { it.id == productId }
    }
}