package com.shoppingcart.estoreapp.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shoppingcart.estoreapp.data.model.Product
/**
 * ProductItem is a Composable function that represents a single product
 * in the shopping application. This function displays the product's
 * details and provides interactive elements for the user. Key features
 * include:
 *
 * - Displaying the product name and description.
 * - Showing the product price.
 * - Providing a button to add the product to the cart.
 * - Making the entire product card clickable to navigate to a detailed
 *   product view.
 *
 * This component is designed to be used within a list of products,
 * where each product can be selected or added to the shopping cart
 * directly from the displayed card.
 *
 * @param product The Product data class containing the product details
 * to be displayed.
 * @param onAddToCart A lambda function to handle the action of adding
 * the product to the cart when the user clicks the "Add to Cart" button.
 * @param onProductClick A lambda function that takes a Product as a
 * parameter, used to handle the action when the product card is clicked.
 */
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
