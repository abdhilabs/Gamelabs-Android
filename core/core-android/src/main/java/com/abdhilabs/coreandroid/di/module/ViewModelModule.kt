package com.abdhilabs.coreandroid.di.module

import androidx.lifecycle.ViewModelProvider
import com.abdhilabs.coreandroid.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//    @ExperimentalCoroutinesApi
//    @FlowPreview
//    @Binds
//    @IntoMap
//    @ViewModelKey(HomeViewModel::class)
//    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailGameViewModel::class)
//    abstract fun provideDetailGameViewModel(viewModel: DetailGameViewModel): ViewModel
}