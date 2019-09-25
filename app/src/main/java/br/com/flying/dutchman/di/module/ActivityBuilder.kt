package br.com.flying.dutchman.di.module

import br.com.flying.dutchman.MainActivity
import br.com.flying.dutchman.ui.movies.MoviesFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [NavHostModule::class, FragmentBuilder::class, FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

}