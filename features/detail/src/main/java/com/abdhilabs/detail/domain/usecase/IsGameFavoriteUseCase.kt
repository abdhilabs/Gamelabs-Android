package com.abdhilabs.detail.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import com.abdhilabs.detail.domain.GameDetailRepository
import javax.inject.Inject

class IsGameFavoriteUseCase @Inject constructor(
    private val repository: GameDetailRepository
) : UseCase<Int, List<GameFavorite>>() {
    override suspend fun invoke(params: Int): List<GameFavorite> {
        return repository.isFavorite(params)
    }
}