package br.com.flying.dutchman.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.flying.dutchman.App
import br.com.flying.dutchman.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class, NetworkModule::class, UseCaseModule::class, RepositoryModule::class])
abstract class AppModule {

    @Binds
    abstract fun provideApp(application: Application): Context

//    @ContributesAndroidInjector(modules = [NavHostModule::class])
//    abstract fun mainActivityInjector(): MainActivity

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSharedPreferences(
            app: Application
        ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
    }
}
