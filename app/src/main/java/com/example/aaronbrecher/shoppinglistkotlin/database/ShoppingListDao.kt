package com.example.aaronbrecher.shoppinglistkotlin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList

/**
 * Created by aaronbrecher on 3/26/18.
 */

@Dao
interface ShoppingListDao {

    @Insert
    fun insertAll(lists: List<ShoppingList>)

    @Insert
    fun insertList(list: ShoppingList): Long

    @Query("SELECT * FROM shoppingLists ORDER BY date")
    fun getShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shoppingLists WHERE name = :listName")
    fun getShoppingList(listName: String): ShoppingList

    @Query("DELETE FROM shoppingLists")
    fun deleteAll()

    @Delete
    fun deleteList(list: ShoppingList)
}