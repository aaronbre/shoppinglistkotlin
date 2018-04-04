package com.example.aaronbrecher.shoppinglistkotlin.activities

import android.app.DialogFragment
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.aaronbrecher.shoppinglistkotlin.R
import com.example.aaronbrecher.shoppinglistkotlin.ShoppingListApplication
import com.example.aaronbrecher.shoppinglistkotlin.fragments.AddCategoryDialog
import com.example.aaronbrecher.shoppinglistkotlin.model.ListItem
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.EditListViewModel
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_list_item.*
import javax.inject.Inject


class EditListItemActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, AddCategoryDialog.NoticeDialogListener{

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    private var category = ""
    private var quantity = 0
    lateinit var mViewModel: EditListViewModel
    lateinit var mCategoriesSpinnerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list_item)
        (application as ShoppingListApplication)
                .appComponent
                .inject(this)

        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(EditListViewModel::class.java)

        //set up the category spinner
        setUpSpinner()

        //set up the UI for for either a new listItem or an edit
        setupForNewOrEdit()
        edit_list_item_quantity_increment.setOnClickListener(quantityChangeListener)
        edit_list_item_quantity_decrement.setOnClickListener(quantityChangeListener)

        //display a dialog if user enters a new category
        edit_list_item_category_button_new.setOnClickListener({view ->
            val dialog = AddCategoryDialog()
            dialog.show(fragmentManager, "addCategoryDialog")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflator = menuInflater
        inflator.inflate(R.menu.menu_with_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.list_action_save){
            val listItem = setupListItem()
            val dbId = mViewModel.addNewListItem(listItem)
            Toast.makeText(this, "The item was successfully entered the id is $dbId", Toast.LENGTH_SHORT).show()
            finish()
            return true
        }else
            return super.onOptionsItemSelected(item)
    }

    private fun setupListItem(): ListItem {
        val listItem = ListItem()
        listItem.itemName = edit_list_item_name.text.toString()
        listItem.notes = edit_list_item_notes.text.toString()
        listItem.quantity = edit_list_item_quantity.text.toString().toInt()
        listItem.category = category
        return listItem

    }

    /**
     * Sets up the UI and data of the category spinner using the
     * category set from the viewModel
     */
    private fun setUpSpinner() {
        val list = ArrayList<String>(mViewModel.categorySet)
        mCategoriesSpinnerAdapter = ArrayAdapter(
                this, R.layout.spinner_item,
                list)
        edit_list_item_category_spinner.adapter = mCategoriesSpinnerAdapter
        edit_list_item_category_spinner.setSelection(0)
        category = mCategoriesSpinnerAdapter.getItem(0)
        edit_list_item_category_spinner.onItemSelectedListener = this
    }

    /**
     * Function to set up the UI for 2 cases
     * 1. If user is editing an existing item fill in all the fields with the relevant information
     * and set the viewModel id to the item's id
     * 2.In case of a new list item leave all fields empty and set the list name from the intent
     */
    private fun setupForNewOrEdit() {
        if(intent.hasExtra(getString(R.string.edit_list_item_list_item_key))){
            val item = intent.getParcelableExtra<ListItem>(getString(R.string.edit_list_item_list_item_key))
            item.let {
                mViewModel.listName = item.listName
                mViewModel.itemId = item.id!!
                title = getString(R.string.edit_list_item_title)
                edit_list_item_name.setText( item.itemName)
                //TODO change this to check if the set has the category if not update this is needed when the app gets
                //a list item from a different user i.e. when we integrate firebase...
                val categoryPosition = mCategoriesSpinnerAdapter.getPosition(item.category)
                edit_list_item_category_spinner.setSelection(categoryPosition)
                category = item.category
                quantity = item.quantity
                edit_list_item_quantity.setText(quantity.toString())
                edit_list_item_notes.setText(item.notes)
            }
        } else{
            title = getString(R.string.new_list_item_title)
            mViewModel.listName = intent.getStringExtra(getString(R.string.edit_list_item_list_name_key))
        }
    }

    val quantityChangeListener = View.OnClickListener { view ->
        val operator = (view as Button).text.trim()
        if(operator == "+"){
            quantity++
            edit_list_item_quantity.text = quantity.toString()
        } else if (operator == "-"){
            quantity--
            edit_list_item_quantity.text = quantity.toString()
        }
    }


    /*
     * Overrides for the onItemSelectedListener
     */
    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Do nothing need to implement for interface
    }

    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        category = adapter?.getItemAtPosition(position) as String
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val dialog = dialog as AddCategoryDialog
        val editText = dialog.view.findViewById<TextInputEditText>(R.id.add_category_edit_text)
        val newCategory = editText.text.toString()
        updateCategories(newCategory)

    }

    private fun updateCategories(newCategory: String) {
        if(mViewModel.categorySet.contains(newCategory)) return
        mViewModel.categorySet.add(newCategory)
        mCategoriesSpinnerAdapter.add(newCategory)
        mCategoriesSpinnerAdapter.notifyDataSetChanged()
        edit_list_item_category_spinner.setSelection(mCategoriesSpinnerAdapter.getPosition(newCategory))
        category = newCategory

    }

}