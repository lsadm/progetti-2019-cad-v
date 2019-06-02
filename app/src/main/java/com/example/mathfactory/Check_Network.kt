package com.example.mathfactory.com.example.mathfactory
import android.content.Context
import android.net.ConnectivityManager
class Check_Network
{
    fun Network_Disponibile(contesto:Context):Boolean
     {
         val gestore_connettività=contesto.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
         val informazioni_network=gestore_connettività.activeNetworkInfo?:return false
         return informazioni_network.isConnected
     }
}