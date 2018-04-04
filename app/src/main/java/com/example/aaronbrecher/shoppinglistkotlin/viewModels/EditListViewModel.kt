package com.example.aaronbrecher.shoppinglistkotlin.viewModels

import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import com.example.aaronbrecher.shoppinglistkotlin.database.ListItemRepository
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import javax.inject.Inject


class EditListViewModel
(val listItemRepository: ListItemRepository, val sharedPreferences: SharedPreferences): ViewModel(){

    lateinit var listName: String
    var categorySet = sharedPreferences.getStringSet("itemCategories", HashSet<String>())
    var itemId = -1L

    fun addNewListItem(item: ListItem): Long{
        item.listName = listName
        if(itemId != -1L) item.id = itemId
        return listItemRepository.insertListItem(item)
    }

    override fun onCleared() {
        super.onCleared()
    }

}