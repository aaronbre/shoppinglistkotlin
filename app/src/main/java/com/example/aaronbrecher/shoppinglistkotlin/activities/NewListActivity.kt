package com.example.aaronbrecher.shoppinglistkotlin.activities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.ShoppingListApplication
import com.example.aaronbrecher.shoppinglistkotlin.model.ShoppingList
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.NewListViewModel
import kotlinx.android.synthetic.main.activity_new_list.*
import javax.inject.Inject

/**
 * Created by aaronbrecher on 3/27/18.
 */
class NewListActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    lateinit var mViewModel: NewListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)
        //set up dagger dependency Injection
        (application as ShoppingListApplication)
                .appComponent
                .inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(NewListViewModel::class.java)

        add_new_list_description_input.onFocusChangeListener = listener
        add_new_list_name_input.onFocusChangeListener = listener

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.menu_with_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId;
        if (id == R.id.list_action_save) {
            val list = createListFromUserInput()
            list?.let {
                val id = mViewModel.addListToDatabase(list)
                if (id == -1L) {
                    Toast.makeText(this, "Failed to add list to database", Toast.LENGTH_SHORT).show()
                    finish()
                } else{
                    Toast.makeText(this, "added to database id is $id", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Function to create a new shoppingList to be used to enter into db. Uses the user's
     * input to create the list and adds the date at time of creation
     * @return returns a new ShoppingList Object. If the List can't be created(i.e. no name) returns null
     */
    private fun createListFromUserInput(): ShoppingList? {
        val list = ShoppingList()
        val name = add_new_list_name_input.text.toString().trim()
        var description = add_new_list_description_input.text.toString().trim()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getString(R.string.no_list_name_error_toast), Toast.LENGTH_LONG).show()
            return null
        }
        if (TextUtils.isEmpty(description)) description = "a $name list"

        list.date = (System.currentTimeMillis())
        list.name = name
        list.description = description
        return list
    }

    /**
     * listener to add and remove the hint from the inputs
     */
    val listener: View.OnFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
        val editText = view as EditText
        var placeholder = "Placeholder"
        if (view === add_new_list_description_input)
            placeholder = getString(R.string.new_list_description_placeholder)
        else if (view === add_new_list_name_input)
            placeholder = getString(R.string.new_list_name_placeholder)
        if (hasFocus)
            editText.hint = placeholder
        else
            editText.hint = ""

    }
}