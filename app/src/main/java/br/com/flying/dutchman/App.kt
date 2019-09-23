package br.com.flying.dutchman

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        fun moviesJson(): String {
            return instance.assets.open("movies.json").bufferedReader().use {
                it.readText()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}