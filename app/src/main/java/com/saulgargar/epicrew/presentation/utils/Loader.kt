package com.saulgargar.epicrew.presentation.utils

import android.content.Context
import androidx.fragment.app.FragmentManager

class Loader(val context: Context?, val fm: FragmentManager){

    private lateinit var loaderDialog : LoaderDialog

    fun show(){
        context?.let {
            loaderDialog = LoaderDialog()
            loaderDialog.isCancelable = false
            loaderDialog.show(fm,"Loader")
        }
    }

    fun dismiss(){
        loaderDialog.dismiss()
    }
}