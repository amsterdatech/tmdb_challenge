package br.com.flying.dutchman.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import br.com.flying.dutchman.di.FragmentKey
import br.com.flying.dutchman.di.factory.InjectingFragmentFactory
import br.com.flying.dutchman.ui.movies.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(MoviesFragment::class)
    abstract fun bindMainFragment(mainFragment: MoviesFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory
}