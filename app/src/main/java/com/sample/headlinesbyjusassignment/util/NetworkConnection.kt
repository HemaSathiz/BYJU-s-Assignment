package com.sample.headlinesbyjusassignment.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkConnection {
    val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
}
