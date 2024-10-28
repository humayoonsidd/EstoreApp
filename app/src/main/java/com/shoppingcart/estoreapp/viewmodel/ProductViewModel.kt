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

/**
 * ProductViewModel is a ViewModel class responsible for managing the UI-related
 * data for the product and cart features in the shopping application. It provides
 * an interface between the UI and the data repository, handling operations
 * such as fetching products, adding items to the cart, and clearing the cart.
 *
 * Key functionalities include:
 * - Exposing a list of products and cart items as StateFlow, allowing UI
 *   components to observe changes and react accordingly.
 * - Fetching products and cart items from the ProductRepository during initialization.
 * - Providing methods to add a product to the cart and clear the cart.
 * - Offering a way to retrieve a product by its ID.
 *
 * This ViewModel is annotated with @HiltViewModel, enabling dependency injection
 * through Hilt for the ProductRepository.
 *
 * @property repository An instance of ProductRepository, which is injected
 *   via constructor injection. It provides methods to access and manipulate
 *   product and cart data.
 */
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