package com.abdhilabs.gamelabs.favourite.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdhilabs.coreandroid.di.ViewModelFactory
import com.abdhilabs.coreandroid.di.ViewModelKey
import com.abdhilabs.gamelabs.favourite.ui.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun provideFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}