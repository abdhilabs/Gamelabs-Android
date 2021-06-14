package com.abdhilabs.detail.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.detail.domain.GameDetailRepository
import javax.inject.Inject

class InsertGameFavouriteUseCase @Inject constructor(
    private val repository: GameDetailRepository
) : UseCase<Game, Unit>() {
    override suspend fun invoke(params: Game) {
        repository.addToFavorite(params)
    }
}