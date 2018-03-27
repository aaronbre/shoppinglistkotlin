package com.example.aaronbrecher.shoppinglistkotlin

import android.arch.lifecycle.ViewModelProvider
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.aaronbrecher.shoppinglistkotlin.viewModels.ListsViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mSharedPrefernces: SharedPreferences

    lateinit var mViewModel: ListsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //do initial app setup on first run...
        if(mSharedPrefernces.getBoolean("firstRun",true)){
            firstTimeSetup()
            mSharedPrefernces.edit().putBoolean("firstRun", false).apply()
        }



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
}
