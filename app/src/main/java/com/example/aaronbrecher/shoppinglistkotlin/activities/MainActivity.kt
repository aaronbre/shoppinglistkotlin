package com.example.aaronbrecher.shoppinglistkotlin.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.ShoppingListApplication
import com.example.aaronbrecher.shoppinglistkotlin.adapters.ShoppingListAdapter
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ListsViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Main Activity will show a list of all lists
 * TODO set up swipe to delete and dialog
 */
class MainActivity : AppCompatActivity(), ShoppingListAdapter.ListClickListener {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mSharedPrefernces: SharedPreferences

    lateinit var mViewModel: ListsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up dagger dependency Injection
        (application as ShoppingListApplication)
                .appComponent
                .inject(this)

        //do initial app setup on first run...
        if(mSharedPrefernces.getBoolean("firstRun",true)){
            firstTimeSetup()
            mSharedPrefernces.edit().putBoolean("firstRun", false).apply()
        }

        //set up the viewModel using custom factory to inject dependenciew
        mViewModel = ViewModelProviders.of(
                this,mViewModelFactory).get(ListsViewModel::class.java)

        //set up the adapter and layout manager of the recycler view
        val adapter = ShoppingListAdapter(null, this)
        shopping_lists_recycler_view.adapter = adapter
        shopping_lists_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //set up the observer to check for changes of the liveData
        val shoppingListsObserver = Observer<List<ShoppingList>>{ lists ->
            lists?.let { adapter.swapLists(lists) }
        }

        mViewModel.getShoppingLists().observe(this, shoppingListsObserver)

        //Set up the FAB to start the new list activity
        add_new_shopping_list_fab.setOnClickListener({view ->
            val intent = Intent(this@MainActivity, NewListActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.shopping_lists_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.lists_activity_action_delete_all){
            mViewModel.deleteAllShoppingLists()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun firstTimeSetup(){
        if(mSharedPrefernces.getBoolean("firstLogin", true)){
            val catagories = HashSet<String>()
            val c = resources.getStringArray(R.array.item_categories)
            Collections.addAll(catagories, *c)
            val editor = mSharedPrefernces.edit()
            editor.putStringSet(getString(R.string.shared_preferences_category_key), catagories)
            editor.apply()
        }
    }

    override fun onlistClick(listName: String) {
        val intent = Intent(this@MainActivity, ListDetailActivity::class.java)
        intent.putExtra("listName", listName)
        startActivity(intent)
    }
}
