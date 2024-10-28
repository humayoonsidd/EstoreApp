package com.shoppingcart.estoreapp.di

import android.content.Context
import androidx.room.Room
import com.shoppingcart.estoreapp.data.database.AppDatabase
import com.shoppingcart.estoreapp.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * AppModule is a Dagger module responsible for providing application-level
 * dependencies. It is annotated with @Module to indicate that it contains
 * methods for providing dependencies and @InstallIn to specify that the
 * provided dependencies should be available in the SingletonComponent,
 * meaning they will have a single instance throughout the app's lifecycle.
 *
 * Key functionalities of this module include:
 *
 * - Providing an instance of the AppDatabase using Room for data persistence.
 * - Providing an instance of the ProductRepository, which serves as a
 *   mediator between the data source (Room database) and the application.
 *
 * The module ensures that dependencies are created and managed by Dagger's
 * dependency injection framework, promoting loose coupling and easier
 * testing.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "shopping_cart_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(database: AppDatabase): ProductRepository {
        return ProductRepository(database)
    }
}
