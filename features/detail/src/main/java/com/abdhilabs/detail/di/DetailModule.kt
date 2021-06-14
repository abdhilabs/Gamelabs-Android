package com.abdhilabs.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdhilabs.coreandroid.di.ViewModelFactory
import com.abdhilabs.coreandroid.di.ViewModelKey
import com.abdhilabs.detail.data.GameDetailRepositoryImpl
import com.abdhilabs.detail.domain.GameDetailRepository
import com.abdhilabs.detail.ui.DetailGameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindAuthRepository(repository: GameDetailRepositoryImpl): GameDetailRepository

    @Binds
    @IntoMap
    @ViewModelKey(DetailGameViewModel::class)
    fun provideHomeViewModel(viewModel: DetailGameViewModel): ViewModel
}