package com.abdhilabs.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import com.abdhilabs.detail.domain.usecase.DeleteGameFavouriteUseCase
import com.abdhilabs.detail.domain.usecase.GetGameUseCase
import com.abdhilabs.detail.domain.usecase.InsertGameFavouriteUseCase
import com.abdhilabs.detail.domain.usecase.IsGameFavoriteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailGameViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val isGameFavoriteUseCase: IsGameFavoriteUseCase,
    private val insertGameFavouriteUseCase: InsertGameFavouriteUseCase,
    private val deleteGameFavouriteUseCase: DeleteGameFavouriteUseCase
) : ViewModel() {

    private val _game = MutableLiveData<Result<Game>>()
    val game: LiveData<Result<Game>>
        get() = _game

    private val _gameFavorite = MutableLiveData<List<GameFavorite>>()
    val gameFavorite: LiveData<List<GameFavorite>>
        get() = _gameFavorite

    fun getGame(idGame: Int) {
        _game.value = Result.Loading
        viewModelScope.launch {
            _game.value = getGameUseCase(idGame)
        }
    }

    fun getIsGameFavorite(idGame: Int) {
        viewModelScope.launch {
            _gameFavorite.value = isGameFavoriteUseCase(idGame)
        }
    }

    fun addToFavorite(game: Game) {
        viewModelScope.launch {
            insertGameFavouriteUseCase(game)
        }
    }

    fun deleteFromFavorite(idGame: Int) {
        viewModelScope.launch {
            deleteGameFavouriteUseCase(idGame)
        }
    }
}