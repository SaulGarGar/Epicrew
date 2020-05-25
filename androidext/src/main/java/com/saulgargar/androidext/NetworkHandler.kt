package com.saulgargar.androidext

import android.content.Context

class NetworkHandler constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
