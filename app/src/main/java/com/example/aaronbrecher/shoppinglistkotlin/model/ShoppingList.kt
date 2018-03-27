package com.example.aaronbrecher.shoppinglistkotlin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Entity(tableName = "shoppingLists")
data class ShoppingList(@PrimaryKey @ColumnInfo var name: String,
                        @ColumnInfo var description: String,
                        @ColumnInfo var date: Long) {
    constructor() : this("", "", 0L)
}