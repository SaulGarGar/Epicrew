package com.saulgargar.androidext

fun Float.roundTwoDecimals(): String{
    return String.format("%.2f",this)
}