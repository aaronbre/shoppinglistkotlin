package com.example.aaronbrecher.shoppinglistkotlin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem

/**
 * Created by aaronbrecher on 3/26/18.
 */

@Dao
interface ListItemDao{
    @Insert
    fun insertAll(items: List<ListItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item: ListItem)

    @Query("SELECT * FROM items WHERE id = :id")
    fun findById(id: Int) : LiveData<ListItem>

    @Query("SELECT * FROM items WHERE list_name = :listName ORDER BY itemName")
    fun findItemsForList(listName: String) : LiveData<List<ListItem>>

    @Delete
    fun deleteItem(item: ListItem)
}