package com.example.aaronbrecher.shoppinglistkotlin.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by aaronbrecher on 3/26/18.
 */

@SuppressLint("ParcelCreator")
@Entity(tableName = "items")
@Parcelize
data class ListItem(@PrimaryKey(autoGenerate = true) var id: Long?,
                    @ColumnInfo var itemName: String,
                    @ColumnInfo var quantity: Int,
                    @ColumnInfo var notes: String,
                    @ColumnInfo(name = "list_name") var listName: String,
                    @ColumnInfo var category: String) : Parcelable {

    constructor() : this(null, "", 0, "", "", "")
}