package com.abdhilabs.detail.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.detail.domain.GameDetailRepository
import javax.inject.Inject

class DeleteGameFavouriteUseCase @Inject constructor(
    private val repository: GameDetailRepository
) : UseCase<Int, Unit>() {
    override suspend fun invoke(params: Int) {
        repository.deleteFromFavorite(params)
    }
}