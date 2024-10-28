package com.shoppingcart.estoreapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * MyApp is the main application class for the E-Store application. It extends
 * the Android Application class and is annotated with @HiltAndroidApp to
 * enable dependency injection using Dagger Hilt.
 *
 * Key Responsibilities:
 * - Acts as the application-level dependency container for Hilt, allowing
 *   the injection of dependencies throughout the application.
 * - Initializes the Hilt components before the application starts, ensuring
 *   all necessary dependencies are available for use in other parts of the
 *   application, such as activities, fragments, and view models.
 *
 * Usage:
 * - This class does not contain any application logic itself but serves as
 *   the entry point for Hilt to handle dependency management.
 * - It ensures that Hilt can create and provide dependencies as needed
 *   across the application, promoting a modular and maintainable codebase.
 */

@HiltAndroidApp
class MyApp : Application()