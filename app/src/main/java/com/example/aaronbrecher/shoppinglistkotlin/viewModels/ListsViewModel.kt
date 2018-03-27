package com.example.aaronbrecher.shoppinglistkotlin.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.aaronbrecher.shoppinglistkotlin.database.ShoppingListRepository
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/26/18.
 */
class ListsViewModel @Inject
constructor(private val repository: ShoppingListRepository) : ViewModel() {

    fun getShoppingLists(): LiveData<List<ShoppingList>> {
        return repository.getAllShoppingLists()
    }

    fun deleteAllShoppingLists(){
        repository.deleteAll()
    }

    fun deleteList(list: ShoppingList){
        repository.deleteList(list)
    }
}