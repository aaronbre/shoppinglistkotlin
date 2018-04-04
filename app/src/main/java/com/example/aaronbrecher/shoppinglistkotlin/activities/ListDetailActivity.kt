package com.example.aaronbrecher.shoppinglistkotlin.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.ShoppingListApplication
import com.example.aaronbrecher.shoppinglistkotlin.adapters.ListDetailAdapter
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ListDetailViewModel
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_list_detail.*
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/27/18.
 * List Detail Screen
 * TODO add swipe to delete functionality
 */
class ListDetailActivity: AppCompatActivity(), ListDetailAdapter.ListItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ListDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        //set up DI
        (application as ShoppingListApplication)
                .appComponent
                .inject(this)

        //set up ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListDetailViewModel::class.java)
        viewModel.listName = intent.getStringExtra("listName")
        setTitle(viewModel.listName)

        val adapter = ListDetailAdapter(null, this)
        list_detail_item_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        list_detail_item_list.adapter = adapter

        val listItemsObserver = Observer<List<ListItem>>{items ->
            items?.let { adapter.swapList(items) }
        }
        viewModel.getAllItemsForCurrentList().observe(this, listItemsObserver)

        list_detail_new_item.setOnClickListener { view ->
            val intent = Intent(this@ListDetailActivity, EditListItemActivity::class.java)
            intent.putExtra(getString(R.string.edit_list_item_list_name_key), viewModel.listName)
            startActivity(intent)
        }
    }

    override fun onlistItemClick(item: ListItem) {
        Log.i("ListDetailActivity", "Interface works")
        val intent = Intent(this, EditListItemActivity::class.java)
        intent.putExtra(getString(R.string.edit_list_item_list_item_key), item)
        startActivity(intent)
    }
}