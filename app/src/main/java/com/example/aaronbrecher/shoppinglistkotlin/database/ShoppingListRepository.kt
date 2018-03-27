package com.example.aaronbrecher.shoppinglistkotlin.database

import android.arch.lifecycle.LiveData
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Singleton
class ShoppingListRepository @Inject
constructor(private val shoppingListDao: ShoppingListDao) {
    fun insertAllLists(lists: List<ShoppingList>){
        shoppingListDao.insertAll(lists)
    }

    fun insertList(list: ShoppingList){
        shoppingListDao.insertList(list)
    }

    fun getAllShoppingLists(): LiveData<List<ShoppingList>>{
        return shoppingListDao.getShoppingLists()
    }

    fun getShoppingList(listName: String): ShoppingList{
        return shoppingListDao.getShoppingList(listName)
    }

    fun deleteAll(){
        shoppingListDao.deleteAll()
    }

    fun deleteList(list: ShoppingList){
        shoppingListDao.deleteList(list)
    }
}