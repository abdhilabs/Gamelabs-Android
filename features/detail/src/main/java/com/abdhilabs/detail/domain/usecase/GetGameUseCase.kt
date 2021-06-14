package com.abdhilabs.detail.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.detail.domain.GameDetailRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameDetailRepository
) : UseCase<Int, Result<Game>>() {
    override suspend fun invoke(params: Int): Result<Game> {
        return repository.getGame(params)
    }
}