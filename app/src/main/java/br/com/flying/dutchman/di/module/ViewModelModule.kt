package br.com.flying.dutchman.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.flying.dutchman.di.ViewModelFactory
import br.com.flying.dutchman.di.ViewModelKey
import br.com.flying.dutchman.ui.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun moviesListViewModel(viewModel: MoviesViewModel): ViewModel

    //Add more ViewModels here
}