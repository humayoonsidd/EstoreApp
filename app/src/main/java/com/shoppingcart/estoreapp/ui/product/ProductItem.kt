package com.shoppingcart.estoreapp.ui.product

import android.content.Intent
import android.net.Uri
import android.text.Html
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shoppingcart.estoreapp.R
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
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick(product) }
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "$${product.price}", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                // Add to Cart Button
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = 2.dp)
                ) {
                    Text("Add to Cart")
                }
                Spacer(modifier = Modifier.width(8.dp))

                // Share Product Details Button
                Button(
                    onClick = {
                        val intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_SUBJECT, product.name)
                            putExtra(Intent.EXTRA_TEXT, product.description)
                            type = "text/plain"
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.share_product_details_str))
                }
            }
        }
    }
}
