package com.example.aaronbrecher.shoppinglistkotlin.dependencyInjection

import com.example.aaronbrecher.shoppinglistkotlin.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}