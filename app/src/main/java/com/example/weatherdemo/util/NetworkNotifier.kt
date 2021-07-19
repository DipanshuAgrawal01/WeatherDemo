package com.example.weatherdemo.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import timber.log.Timber

object NetworkNotifier {

    private val privateNetworkRestoredEvent = MutableLiveData<Event<Unit>>()
    val networkRestoredEvent: LiveData<Event<Unit>> = privateNetworkRestoredEvent

    @Volatile
    private var isNetworkAvailable = false

    private var connectivityManager: ConnectivityManager? = null

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Handler().postDelayed({
                if (!isNetworkAvailable && !connectivityManager?.allNetworks.isNullOrEmpty()) {
                    Timber.i("Network connection was restored, let's notify observers!")
                    privateNetworkRestoredEvent.postValue(Event(Unit))
                }
                isNetworkAvailable = true
                Timber.i("Some network became available. Available networks count: ${connectivityManager?.allNetworks?.size}")
            }, 1000)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Handler().postDelayed({
                isNetworkAvailable = !connectivityManager?.allNetworks.isNullOrEmpty()
                Timber.i("Some network was lost. Available networks count: ${connectivityManager?.allNetworks?.size}")
            }, 1000)
        }
    }

    fun turnOnUpdates(activity: Activity) {
        Timber.i("Updates are turned on")
        connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        isNetworkAvailable = !connectivityManager?.allNetworks.isNullOrEmpty()
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun turnOffUpdates() {
        Timber.i("Updates are turned off")
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }
}