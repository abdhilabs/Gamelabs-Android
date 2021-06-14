package com.abdhilabs.home.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.home.domain.GameRepository
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val repository: GameRepository
) : UseCase<Int, Result<Game>>() {
    override suspend fun invoke(params: Int): Result<Game> {
        return repository.getGame(params)
    }
}