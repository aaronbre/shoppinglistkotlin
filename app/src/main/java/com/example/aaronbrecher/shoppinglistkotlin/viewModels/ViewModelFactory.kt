package com.example.aaronbrecher.shoppinglistkotlin.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import com.example.aaronbrecher.shoppinglistkotlin.database.ListItemRepository
import com.example.aaronbrecher.shoppinglistkotlin.database.ShoppingListRepository
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/26/18.
 */
class ViewModelFactory @Inject
constructor(val listItemRepository: ListItemRepository,
            val shoppingListRepository: ShoppingListRepository,
            val sharedPreferences: SharedPreferences): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListsViewModel::class.java))
            return ListsViewModel(shoppingListRepository) as T
        else if(modelClass.isAssignableFrom(NewListViewModel::class.java))
            return NewListViewModel(shoppingListRepository) as T
        else if(modelClass.isAssignableFrom(ListDetailViewModel::class.java))
            return ListDetailViewModel(shoppingListRepository, listItemRepository) as T
        else if(modelClass.isAssignableFrom(EditListViewModel::class.java))
            return EditListViewModel(listItemRepository, sharedPreferences) as T
        else
            throw IllegalArgumentException("ViewModel not found")
    }

}