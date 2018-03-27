package com.example.aaronbrecher.shoppinglistkotlin

import android.app.Application
import com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection.AppComponent
import com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection.AppModule
import com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection.DaggerAppComponent
import com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection.RoomModule

/**
 * Created by aaronbrecher on 3/26/18.
 */
class ShoppingListApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        app = this
        appComponent = DaggerAppComponent.builder()
                .roomModule(RoomModule(this))
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        lateinit var app: ShoppingListApplication
    }
}