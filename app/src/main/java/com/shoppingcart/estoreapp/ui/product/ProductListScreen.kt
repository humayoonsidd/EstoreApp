package com.shoppingcart.estoreapp.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoppingcart.estoreapp.data.model.Product
import com.shoppingcart.estoreapp.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    onProductClick: (Product) -> Unit,
    onCartClick: () -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsState().value
    val cartItems = viewModel.cartItems.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(products) { product ->
                ProductItem(product, onAddToCart = { viewModel.addToCart(product) }, onProductClick = onProductClick)
            }
        }

        // Display cart count
        Button(
            onClick = onCartClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Go to Cart (${cartItems.size})")

        }
        // Use a Spacer to push the button down
        Spacer(modifier = Modifier.weight(0.1f)) // Takes up remaining space

    }
}