package com.shoppingcart.estoreapp.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shoppingcart.estoreapp.data.model.Product

@Composable
fun ProductItem(
    product: Product,
    onAddToCart: () -> Unit,
    onProductClick: (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick(product) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodySmall)

            // Row for price and button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "$${product.price}", style = MaterialTheme.typography.titleMedium)

                Button(onClick = onAddToCart) {
                    Text("Add to Cart")
                }
            }
        }
    }
}
