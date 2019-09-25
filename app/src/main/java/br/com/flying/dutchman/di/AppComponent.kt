package br.com.flying.dutchman.di

import android.app.Application
import br.com.flying.dutchman.App
import br.com.flying.dutchman.di.module.ActivityBuilder
import br.com.flying.dutchman.di.module.AppModule
import br.com.flying.dutchman.di.module.FragmentBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}