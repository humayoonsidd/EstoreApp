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
