package com.shoppingcart.estoreapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shoppingcart.estoreapp.data.converter.ProductTypeConverter

/**
 * CartItem represents an item in the shopping cart within the shopping
 * cart application. This data class is annotated with @Entity to indicate
 * that it should be persisted in the Room database, with its data stored
 * in the "cart_items" table.
 *
 * The class contains the following properties:
 *
 * - cartItemId: A unique identifier for each cart item. It is marked with
 *   @PrimaryKey and set to auto-generate, meaning the database will
 *   automatically assign a value when a new CartItem is inserted.
 *
 * - productId: An identifier for the associated product. This field links
 *   the cart item to a specific product in the products table.
 *
 * - quantity: An integer representing the number of units of the product
 *   in the cart. This field allows for tracking how many of the same
 *   product the user wishes to purchase.
 *
 * - product: An instance of the Product class, which contains details about
 *   the product associated with this cart item. The @TypeConverters annotation
 *   indicates that the ProductTypeConverter will be used to convert the
 *   Product object into a format suitable for storage in the database.
 */
@Entity(tableName = "cart_items")
@TypeConverters(ProductTypeConverter::class)
data class CartItem(
    @PrimaryKey(autoGenerate = true) val cartItemId: Int = 0,
    val productId: Int,
    var quantity: Int,
    var product: Product?
)
