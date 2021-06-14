package com.abdhilabs.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdhilabs.coreandroid.di.ViewModelFactory
import com.abdhilabs.coreandroid.di.ViewModelKey
import com.abdhilabs.home.data.GameRepositoryImpl
import com.abdhilabs.home.domain.GameRepository
import com.abdhilabs.home.ui.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindAuthRepository(repository: GameRepositoryImpl): GameRepository

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel
}