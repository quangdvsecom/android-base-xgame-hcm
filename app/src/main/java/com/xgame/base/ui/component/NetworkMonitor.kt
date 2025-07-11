package com.xgame.base.ui.component

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _isConnected = MutableStateFlow(checkCurrentConnection())
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isConnected.value = true
        }

        override fun onLost(network: Network) {
            _isConnected.value = checkCurrentConnection()
        }

        override fun onUnavailable() {
            _isConnected.value = false
        }
    }

    fun registerCallback() {
        val request =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(request, callback)
    }

    fun unregisterCallback() {
        try {
            connectivityManager.unregisterNetworkCallback(callback)
        } catch (_: Exception) {
        }
    }

    private fun checkCurrentConnection(): Boolean {
        var activeNetwork: Network? = null
        if (connectivityManager?.activeNetwork == null) {
            return false
        }else
          activeNetwork = connectivityManager.activeNetwork

        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

