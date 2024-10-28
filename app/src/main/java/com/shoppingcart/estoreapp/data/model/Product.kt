package com.shoppingcart.estoreapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Product represents a product available for purchase in the shopping
 * cart application. This data class is annotated with @Entity to indicate
 * that it should be persisted in the Room database, with its data stored
 * in the "products" table.
 *
 * The class contains the following properties:
 *
 * - id: A unique identifier for each product. This field is marked with
 *   @PrimaryKey, ensuring that each product has a distinct value in the
 *   database, which helps in maintaining data integrity and enabling
 *   efficient retrieval.
 *
 * - name: A string representing the name of the product. This field holds
 *   the product's name, which is displayed to users in the user interface.
 *
 * - description: A string providing a detailed description of the product.
 *   This field gives potential buyers more information about the product,
 *   helping them make informed purchasing decisions.
 *
 * - price: A double representing the cost of the product. This field holds
 *   the product's price, which is essential for checkout and cart operations.
 */
@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val price: Double
)
