package br.com.flying.dutchman

import android.app.Application
import android.content.Context
import br.com.flying.dutchman.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

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

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

}