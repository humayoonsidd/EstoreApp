package com.shoppingcart.estoreapp.ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shoppingcart.estoreapp.data.model.Product

/**
 * ProductDetailScreen is a Composable function that displays detailed information
 * about a specific product, including its name, description.
 * It also provides an "Add to Cart" button, allowing users to add the product
 * to their shopping cart. The screen is designed using Material Design components
 * for a modern and user-friendly interface.
 *
 * @param product The Product object containing the details to be displayed.
 * @param onAddToCart A lambda function that defines the action to perform
 *  when the "Add to Cart" button is clicked.
 */
@Composable
fun ProductDetailScreen(product: Product, onAddToCart: () -> Unit) {
    // Adding a background color to the entire screen
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8F9FA))) {
        // Card layout to encapsulate product details
        Card(
            shape = RoundedCornerShape(16.dp), // Rounded corners for the card
            elevation = CardDefaults.cardElevation(8.dp), // Shadow effect for the card
            modifier = Modifier
                .padding(16.dp) // Padding around the card
                .fillMaxSize() // Fills the maximum size of the screen
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp), // Padding inside the card
                verticalArrangement = Arrangement.spacedBy(16.dp) // Spacing between elements
            ) {

                // Displaying the product name in a large, bold style
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineLarge, // Larger headline typography style
                    modifier = Modifier.fillMaxWidth() // Fills the maximum width available
                )

                // Displaying the product description in a larger body style
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium, // Uses the medium body typography style
                    modifier = Modifier.fillMaxWidth() // Fills the maximum width available
                )

                // Displaying the product price aligned to the end of the screen
                Text(
                    text = "Price: $${product.price}", // Shows the price with a dollar sign
                    style = MaterialTheme.typography.titleMedium, // Uses the title medium typography style
                    textAlign = TextAlign.End, // Aligns the text to the end (right side)
                    modifier = Modifier.fillMaxWidth() // Fills the maximum width available
                )
            }
        }
    }
}
