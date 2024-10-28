package com.shoppingcart.estoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.shoppingcart.estoreapp.ui.cart.CartScreen
import com.shoppingcart.estoreapp.ui.product.ProductDetailScreen
import com.shoppingcart.estoreapp.ui.product.ProductListScreen
import com.shoppingcart.estoreapp.ui.theme.EStoreAppTheme
import com.shoppingcart.estoreapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
/**
 * MainActivity serves as the entry point for the E-Store application. It extends
 * ComponentActivity and is annotated with @AndroidEntryPoint to support dependency
 * injection via Hilt.
 *
 * Key Responsibilities:
 * - Initializes the app and sets the main content using Jetpack Compose.
 * - Enables edge-to-edge display for a modern user interface experience.
 * - Defines the main navigation structure of the application using Jetpack Navigation.
 * - Hosts the ProductListScreen as the start destination, allowing users to browse
 *   products, view product details, and access the shopping cart.
 *
 * The activity's composable structure includes:
 * - A NavHost that manages navigation between screens (product list, product details, cart).
 * - Click handling for navigating to product details and cart.
 *
 * Hilt is used for ViewModel injection, ensuring the ProductViewModel is available
 * within the composables for managing product-related data and actions.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            EStoreAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            ProductListScreen(
                onProductClick = { product ->
                    navController.navigate("productDetail/${product.id}")
                },
                onCartClick = {
                    navController.navigate("cart")
                }
            )
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt() ?: return@composable
            val viewModel: ProductViewModel = hiltViewModel()
            val product = viewModel.getProductById(productId)
            if (product != null) {
                ProductDetailScreen(product = product) {
                    viewModel.addToCart(product)
                }
            }
        }
        composable("cart") {
            CartScreen()
        }
    }
}

