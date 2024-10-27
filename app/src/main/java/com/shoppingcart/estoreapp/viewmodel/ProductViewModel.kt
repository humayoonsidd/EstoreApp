package com.shoppingcart.estoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product
import com.shoppingcart.estoreapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    val cartItems: StateFlow<List<CartItem>> = productRepository.cartItems

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = productRepository.getProducts()
    }

    fun addToCart(product: Product) {
        productRepository.addToCart(product)
    }
}