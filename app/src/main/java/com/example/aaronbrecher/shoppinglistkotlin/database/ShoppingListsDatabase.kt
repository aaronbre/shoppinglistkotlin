package com.example.aaronbrecher.shoppinglistkotlin.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Database(entities = arrayOf(ShoppingList::class, ListItem::class), version = 0)
abstract class ShoppingListsDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun listItemDao(): ListItemDao
}