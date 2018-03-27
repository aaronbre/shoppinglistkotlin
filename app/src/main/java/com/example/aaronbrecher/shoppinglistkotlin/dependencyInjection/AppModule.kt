package com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.aaronbrecher.shoppinglistkotlin.ShoppingListApplication
import com.example.aaronbrecher.shoppinglistkotlin.database.ListItemRepository
import com.example.aaronbrecher.shoppinglistkotlin.database.ShoppingListRepository
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */

@Module
class AppModule(private val application: ShoppingListApplication) {

    @Provides
    @Singleton
    fun provideShoppingListApp(): ShoppingListApplication {
        return application
    }

    @Provides
    @Singleton
    fun provideApp(): Application{
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(listItemRepository: ListItemRepository,
                                shoppingListRepository: ShoppingListRepository,
                                sharedPreferences: SharedPreferences): ViewModelProvider.Factory{
        return ViewModelFactory(listItemRepository,shoppingListRepository,sharedPreferences)
    }
}