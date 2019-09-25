package br.com.flying.dutchman

import android.app.Application
import android.content.Context
import android.os.StrictMode
import br.com.flying.dutchman.di.DaggerAppComponent
import com.facebook.stetho.Stetho
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

        Stetho.initializeWithDefaults(this)

//        StrictMode.setThreadPolicy(
//            StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .penaltyDialog()
//                .build()
//        )
//        StrictMode.setVmPolicy(
//            StrictMode.VmPolicy.Builder().detectAll()
//                .penaltyLog()
//                .build()
//        )
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