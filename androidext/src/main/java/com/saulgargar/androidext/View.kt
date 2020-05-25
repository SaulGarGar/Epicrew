package com.saulgargar.androidext

import android.view.View
import androidx.core.content.ContextCompat

fun View.backgroundColor(id: Int) { setBackgroundColor(ContextCompat.getColor(context, id)) }
