package br.com.flying.dutchman.di.module

import br.com.flying.dutchman.ui.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector()
    internal abstract fun contributeMoviesFragment(): MoviesFragment
}