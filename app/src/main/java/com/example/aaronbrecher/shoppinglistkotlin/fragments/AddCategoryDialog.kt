package com.example.aaronbrecher.shoppinglistkotlin.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.aaronbrecher.shoppinglistkotlin.R


class AddCategoryDialog: DialogFragment(){

    lateinit var mListener: NoticeDialogListener
    lateinit var mView: View

    interface NoticeDialogListener{
        fun onDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        mView = inflater.inflate(R.layout.dialog_add_category, null)

        builder.setTitle(R.string.category_dialog_title)
                .setView(mView)
                .setNegativeButton(R.string.category_dialog_cancel, {dialogInterface, i ->
                    this@AddCategoryDialog.dialog.cancel()})
                .setPositiveButton(R.string.category_dialog_add_category, {dialogInterface, i ->
                    mListener.onDialogPositiveClick(this@AddCategoryDialog)})

        return builder.create()
    }

    override fun getView(): View {
        return mView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try{
            mListener = context as NoticeDialogListener
        } catch(e: ClassCastException){
            throw ClassCastException("${context.toString()} must implement NoticeDialogListener")
        }
    }
}