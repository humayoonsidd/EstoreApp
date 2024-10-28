package com.shoppingcart.estoreapp.data.dao

import androidx.room.*
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product

/**
 * CartItemDao is an interface that defines data access methods for managing
 * cart items in the Room database. It provides methods to perform CRUD operations
 * (Create, Read, Update, Delete) on the CartItem entity and allows retrieval
 * of all products stored in the database.
 *
 * The interface is annotated with @Dao (Data Access Object), indicating that it
 * serves as a bridge between the app's data layer and the Room database.
 * Each method is annotated with Room annotations to define SQL queries and
 * the operations to be performed.
 */
@Dao
interface CartItemDao {

    /**
     * Retrieves a specific CartItem from the database based on the productId.
     *
     * @param productId The ID of the product for which to fetch the CartItem.
     * @return The CartItem associated with the specified productId, or null
     *         if no CartItem is found.
     */
    @Query("SELECT * FROM cart_items WHERE productId = :productId LIMIT 1")
    suspend fun getCartItemByProductId(productId: Int): CartItem?

    /**
     * Inserts a CartItem into the database. If a CartItem with the same
     * productId already exists, it will be replaced.
     *
     * @param cartItem The CartItem object to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    /**
     * Updates an existing CartItem in the database.
     *
     * @param cartItem The CartItem object containing updated information.
     */
    @Update
    suspend fun update(cartItem: CartItem)

    /**
     * Clears all CartItems from the database.
     */
    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    /**
     * Retrieves all CartItems from the database.
     *
     * @return A list of all CartItem objects stored in the database.
     */
    @Query("SELECT * FROM cart_items")
    suspend fun getAllCartItems(): List<CartItem>

    /**
     * Retrieves all products from the database.
     *
     * @return A list of all Product objects stored in the database.
     */
    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Product>

    /**
     * Inserts multiple Product objects into the database. If a Product with
     * the same ID already exists, it will be replaced.
     *
     * @param products A list of Product objects to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)
}
