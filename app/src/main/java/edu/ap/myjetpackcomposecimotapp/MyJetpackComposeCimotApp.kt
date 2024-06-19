package edu.ap.myjetpackcomposecimotapp

import android.app.Application

class MyJetpackComposeCimotApp : Application() {

    companion object {
        lateinit var instance : MyJetpackComposeCimotApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}