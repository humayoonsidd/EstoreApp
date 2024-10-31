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

/**
 * ProductListScreen is a Composable function that displays a list of products
 * available in the shopping application. It retrieves the product data
 * from the ProductViewModel and presents each product in a vertically
 * scrollable list. Users can interact with the product items to view
 * details or add them to their shopping cart.
 *
 * Key features include:
 * - A lazy column that efficiently displays the list of products.
 * - A button to navigate to the shopping cart, showing the number of items
 *   currently in the cart.
 * - Integration with Hilt for dependency injection, allowing easy access
 *   to the ProductViewModel.
 *
 * @param onProductClick A lambda function that takes a Product as a
 * parameter, used to handle the action when a product item is clicked.
 * This usually leads to a detailed view of the product.
 * @param onCartClick A lambda function that handles the action when
 * the user clicks on the cart button, typically used to navigate to
 * the cart screen.
 * @param viewModel The ProductViewModel instance provided by Hilt.
 * If not provided, it defaults to the Hilt ViewModel instance for
 * this composable.
 */
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