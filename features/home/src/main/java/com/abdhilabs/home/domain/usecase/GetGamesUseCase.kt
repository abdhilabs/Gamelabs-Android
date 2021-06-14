package com.abdhilabs.home.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.home.domain.GameRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) : UseCase<UseCase.None, Result<List<Game>>>() {
    override suspend fun invoke(params: None): Result<List<Game>> {
        return repository.getGames()
    }
}