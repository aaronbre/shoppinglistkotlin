package com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection

import android.app.Application
import android.arch.persistence.room.Room
import com.example.aaronbrecher.shoppinglistkotlin.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */

@Module
class RoomModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideShoppingListDatabase(application: Application): ShoppingListsDatabase{
        return Room.databaseBuilder(application,
                ShoppingListsDatabase::class.java,
                "shopping-list-database")
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideShoppingListDao(shoppingListsDatabase: ShoppingListsDatabase): ShoppingListDao{
        return shoppingListsDatabase.shoppingListDao()
    }

    @Provides
    @Singleton
    fun provideListItemDao(shoppingListsDatabase: ShoppingListsDatabase): ListItemDao{
        return shoppingListsDatabase.listItemDao()
    }

    @Provides
    @Singleton
    fun provideListItemRepository(listItemDao: ListItemDao): ListItemRepository{
        return ListItemRepository(listItemDao)
    }

    @Provides
    @Singleton
    fun provideShoppingListRepository(shoppingListDao: ShoppingListDao): ShoppingListRepository{
        return ShoppingListRepository(shoppingListDao)
    }

}