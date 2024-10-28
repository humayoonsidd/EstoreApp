package com.shoppingcart.estoreapp.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoppingcart.estoreapp.R
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.viewmodel.ProductViewModel

@Composable
fun CartScreen(viewModel: ProductViewModel = hiltViewModel()) {
    val cartItems = viewModel.cartItems.collectAsState().value
    val total = cartItems.sumOf { it.product?.price?.times(it.quantity) ?:0.0  }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.e_store_cart_str),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(cartItems) { cartItem ->
                CartItemRow(cartItem)
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Total Cost
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.total_str),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "$${"%.2f".format(total)}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }

        // Checkout Button
        Button(
            onClick = { /* Handle checkout action */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 46.dp)
        ) {
            Text(text = "Proceed to Checkout", fontSize = 18.sp)
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                cartItem.product?.let {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Quantity: ${cartItem.quantity}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Text(
                text = "$${"%.2f".format((cartItem.product?.price ?: 0.0) * cartItem.quantity)}",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}
