package br.com.flying.dutchman.di.module

import br.com.flying.dutchman.ui.movies.MoviesFragment
import dagger.Module
import dagger.Provides


@Module
class FragmentModule {

    @Provides
    fun providesFragment(): MoviesFragment {
        return MoviesFragment()
    }
}