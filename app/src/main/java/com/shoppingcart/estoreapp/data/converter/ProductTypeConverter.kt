package com.shoppingcart.estoreapp.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.shoppingcart.estoreapp.data.model.Product

/**
 * ProductTypeConverter is a class that provides methods to convert the Product
 * object to and from a String representation, using JSON serialization and
 * deserialization with the Gson library. This is particularly useful for storing
 * Product objects in a Room database, where only primitive types and Strings
 * can be stored directly.
 *
 * The methods defined in this class are annotated with @TypeConverter, allowing
 * Room to automatically convert between the Product type and its String
 * representation when writing to or reading from the database.
 */
class ProductTypeConverter {

    /**
     * Converts a Product object to its JSON String representation.
     *
     * @param product The Product object to be converted. It can be null.
     * @return A JSON String representation of the Product object or null if the
     *         product is null.
     */
    @TypeConverter
    fun fromProduct(product: Product?): String? {
        return Gson().toJson(product)
    }

    /**
     * Converts a JSON String representation back to a Product object.
     *
     * @param productString The JSON String representation of a Product. It can be null.
     * @return A Product object or null if the input string is null or cannot be parsed.
     */
    @TypeConverter
    fun toProduct(productString: String?): Product? {
        return Gson().fromJson(productString, Product::class.java)
    }
}
