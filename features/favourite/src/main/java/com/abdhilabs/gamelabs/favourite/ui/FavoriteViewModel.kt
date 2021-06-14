package com.abdhilabs.gamelabs.favourite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.db.entity.toGame
import com.abdhilabs.gamelabs.favourite.domain.GetGameFavoriteUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getGameFavoriteUseCase: GetGameFavoriteUseCase
): ViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>>
        get() = _games

    fun getGamesFavorite() {
        viewModelScope.launch {
            val games = getGameFavoriteUseCase(UseCase.None).first()
            _games.value = games.map { it.toGame() }
        }
    }
}