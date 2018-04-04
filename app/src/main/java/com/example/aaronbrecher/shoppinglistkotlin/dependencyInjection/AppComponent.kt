package com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection

import com.example.aaronbrecher.shoppinglistkotlin.activities.EditListItemActivity
import com.example.aaronbrecher.shoppinglistkotlin.activities.ListDetailActivity
import com.example.aaronbrecher.shoppinglistkotlin.activities.MainActivity
import com.example.aaronbrecher.shoppinglistkotlin.activities.NewListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(newListActivity: NewListActivity)
    fun inject(listDetailActivity: ListDetailActivity)
    fun inject(editListItemActivity: EditListItemActivity)
}