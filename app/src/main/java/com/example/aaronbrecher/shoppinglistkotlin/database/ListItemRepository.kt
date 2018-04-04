package com.example.aaronbrecher.shoppinglistkotlin.database

import android.arch.lifecycle.LiveData
import com.example.aaronbrecher.shoppinglistkotlin.database.ListItemDao
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Singleton
class ListItemRepository @Inject
constructor(private val listItemDao: ListItemDao) {

    fun getListItemForId(id: Int): LiveData<ListItem> {
        return listItemDao.findById(id)
    }

    fun getListItemsForShoppingList(listName: String): LiveData<List<ListItem>> {
        return listItemDao.findItemsForList(listName)
    }

    fun insertListItems(items: List<ListItem>) {
        listItemDao.insertAll(items)
    }

    fun insertListItem(item: ListItem): Long {
        return listItemDao.insertItem(item)
    }

    fun deleteItem(item: ListItem) {
        listItemDao.deleteItem(item)
    }
}