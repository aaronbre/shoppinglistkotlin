package com.example.aaronbrecher.shoppinglistkotlin.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem


/**
 * Created by aaronbrecher on 2/22/18.
 * Adapter for list detail activity recyclerView
 */

class ListDetailAdapter(private var mListItems: List<ListItem>?, private val mListItemClickListener: ListItemClickListener)
    : RecyclerView.Adapter<ListDetailAdapter.ListDetailViewHolder>() {

    interface ListItemClickListener {
        fun onlistItemClick(item: ListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_detail_list_item, parent, false)
        return ListDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListDetailViewHolder, position: Int) {
        val item = mListItems?.get(position)
        if (item != null) {
            holder.itemName.text = item.itemName
            holder.itemCategory.text = item.category
            holder.itemNotes.text = item.notes
            holder.itemQuantity.text = item.quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return mListItems?.size ?: 0
    }

    /**
     * The ViewHolder for the adapter. This viewHolder will implement the on click listener
     * for the enclosed view. It uses the context member variable to launch a new activity to see the
     * details of the listItem and be able to edit it... again not sure if this is best practice...
     */
    inner class ListDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var itemName: TextView = itemView.findViewById(R.id.list_detail_item_name)
        internal var itemCategory: TextView = itemView.findViewById(R.id.list_detail_item_category)
        internal var itemNotes: TextView = itemView.findViewById(R.id.list_detail_item_notes)
        internal var itemQuantity: TextView = itemView.findViewById(R.id.list_detail_item_quantity)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            val item = mListItems!!.get(adapterPosition)
            Log.i("ListDetailAdapter", "onClick!!")
            mListItemClickListener.onlistItemClick(item)
        }
    }

    fun swapList(newList: List<ListItem>) {
        mListItems = newList
    }
}
