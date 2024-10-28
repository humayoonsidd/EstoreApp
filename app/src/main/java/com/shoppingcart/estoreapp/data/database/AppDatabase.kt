package com.shoppingcart.estoreapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shoppingcart.estoreapp.data.converter.ProductTypeConverter
import com.shoppingcart.estoreapp.data.dao.CartItemDao
import com.shoppingcart.estoreapp.data.model.CartItem
import com.shoppingcart.estoreapp.data.model.Product

/**
 * AppDatabase is an abstract class that represents the Room database for
 * the shopping cart application. It serves as the main access point for
 * the underlying SQLite database and is responsible for creating and
 * managing the database instance.
 *
 * The class is annotated with @Database, indicating that it is a Room
 * database and specifying the entities it will contain. In this case,
 * the database includes two entities: Product and CartItem. The version
 * of the database is set to 1, which can be incremented in future
 * updates to handle schema changes.
 *
 * The @TypeConverters annotation indicates that the specified
 * ProductTypeConverter class should be used for converting custom
 * types (such as the Product class) to and from SQLite-compatible
 * formats.
 *
 * This class provides an abstract method, cartItemDao(), which
 * returns an instance of CartItemDao. This DAO interface contains
 * methods for accessing and manipulating cart items within the
 * database.
 */
@Database(entities = [Product::class, CartItem::class], version = 1)
@TypeConverters(ProductTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Provides an instance of CartItemDao for accessing cart items
     * in the database.
     *
     * @return CartItemDao instance to perform CRUD operations on
     *         CartItem entities.
     */
    abstract fun cartItemDao(): CartItemDao
}
