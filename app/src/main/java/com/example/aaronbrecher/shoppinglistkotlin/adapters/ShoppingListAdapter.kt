package com.example.aaronbrecher.shoppinglistkotlin.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList

/**
 * Created by aaronbrecher on 3/26/18.
 */
class ShoppingListAdapter(private var mShoppingLists: List<ShoppingList>?, private val mListItemClickListener: ListItemClickListener)
    : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    interface ListItemClickListener {
        fun onlistItemClick(listName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val layoutId = R.layout.shopping_lists_list_item
        val view = inflater.inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = mShoppingLists?.get(position)
        if(list != null){
            holder.listName.text = list.name
            holder.listDescription.text = list.description
            holder.listDate.text = list.formattedDate

        }
    }

    fun swapLists(newLists: List<ShoppingList>){
        mShoppingLists = newLists
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mShoppingLists?.size!! ?: 0
    }



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val listName: TextView
        val listDescription: TextView
        val listDate: TextView
        init {
            itemView.setOnClickListener(this)
            listName = itemView.findViewById(R.id.shopping_lists_text_main)
            listDescription = itemView.findViewById(R.id.shopping_lists_text_secondary)
            listDate = itemView.findViewById(R.id.shopping_lists_text_date)
        }

        override fun onClick(v: View?) {
            val adapterPosition = adapterPosition
            val listName = mShoppingLists?.get(adapterPosition)?.name ?: ""
            mListItemClickListener.onlistItemClick(listName)
        }

    }
}