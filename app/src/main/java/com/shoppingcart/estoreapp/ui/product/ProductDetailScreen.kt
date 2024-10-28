package com.shoppingcart.estoreapp.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shoppingcart.estoreapp.data.model.Product

@Composable
fun ProductDetailScreen(product: Product, onAddToCart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.displayMedium)
        Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "Price: $${product.price}",
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onAddToCart, modifier = Modifier.align(Alignment.End)) {
            Text("Add to Cart")
        }
    }
}
