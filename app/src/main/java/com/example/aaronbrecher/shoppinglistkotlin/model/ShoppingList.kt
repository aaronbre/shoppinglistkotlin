package com.example.aaronbrecher.shoppinglistkotlin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.DateFormat

/**
 * Created by aaronbrecher on 3/26/18.
 */
@Entity(tableName = "shoppingLists")
data class ShoppingList(@PrimaryKey @ColumnInfo var name: String,
                        @ColumnInfo var description: String,
                        @ColumnInfo var date: Long) {
    val formattedDate: String
        get() = DateFormat.getDateInstance().format(date)

    constructor() : this("", "", 0L)
}