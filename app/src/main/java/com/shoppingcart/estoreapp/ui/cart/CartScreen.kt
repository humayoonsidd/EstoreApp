package com.shoppingcart.estoreapp.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoppingcart.estoreapp.viewmodel.ProductViewModel

@Composable
fun CartScreen(viewModel: ProductViewModel = hiltViewModel()) {
    val cartItems = viewModel.cartItems.collectAsState().value
    val total = cartItems.sumOf { it.total }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems.size) { index ->
                val cartItem = cartItems[index]
                Text(
                    text = "${cartItem.product.name} - Quantity: ${cartItem.quantity} - Total: $${cartItem.total}",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
        Divider()
        Text(
            text = "Total Cost: $${"%.2f".format(total)}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.End).padding(vertical = 8.dp)
        )
    }
}
