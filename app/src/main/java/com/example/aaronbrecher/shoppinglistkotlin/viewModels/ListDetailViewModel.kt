package com.example.aaronbrecher.shoppinglistkotlin.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.aaronbrecher.shoppinglistkotlin.database.ListItemRepository
import com.example.aaronbrecher.shoppinglistkotlin.database.ShoppingListRepository
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/28/18.
 */
class ListDetailViewModel(val shoppingListRepository: ShoppingListRepository,
                          val listItemRepository: ListItemRepository): ViewModel(){
    var listName = ""

    fun getAllItemsForCurrentList(): LiveData<List<ListItem>>{
        return listItemRepository.getListItemsForShoppingList(listName)
    }

    fun deleteList(){
        shoppingListRepository.deleteList(shoppingListRepository.getShoppingList(listName))
    }
}