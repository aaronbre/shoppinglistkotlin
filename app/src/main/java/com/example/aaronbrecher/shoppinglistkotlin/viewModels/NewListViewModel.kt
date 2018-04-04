package com.example.aaronbrecher.shoppinglistkotlin.viewModels

import android.arch.lifecycle.ViewModel
import com.example.aaronbrecher.shoppinglistkotlin.database.ShoppingListRepository
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/27/18.
 */
class NewListViewModel(private val repository: ShoppingListRepository): ViewModel() {

    fun addListToDatabase(list: ShoppingList): Long{
        return repository.insertList(list)
    }
}