package com.handen.productlist.list

import android.content.Context
import android.net.ConnectivityManager


interface ConnectivityManagerService {
    fun checkHaveInternetConnection(): Boolean
}

class ConnectivityManagerServiceImpl(
    private val context: Context
) : ConnectivityManagerService {
    override fun checkHaveInternetConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}