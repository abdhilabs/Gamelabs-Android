package com.abdhilabs.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.home.domain.usecase.GetGamesUseCase
import com.abdhilabs.home.domain.usecase.GetSearchGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val searchGamesUseCase: GetSearchGamesUseCase
) : ViewModel() {

    private val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    private val _games = MutableLiveData<Result<List<Game>>>()
    val games: LiveData<Result<List<Game>>>
        get() = _games

    fun getGames() {
        _games.value = Result.Loading
        viewModelScope.launch {
            _games.value = getGamesUseCase(UseCase.None)
        }
    }

    fun getSearchGames(query: String) {
        _games.value = Result.Loading
        viewModelScope.launch {
            queryChannel.send(query)
            _games.value = queryChannel.asFlow()
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.trim().isNotEmpty() }
                .mapLatest { searchGamesUseCase(query) }
                .first()
        }
    }
}