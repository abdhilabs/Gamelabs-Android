package com.abdhilabs.home.domain.usecase

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.data.entity.Game
import com.abdhilabs.coreandroid.data.vo.Result
import com.abdhilabs.home.domain.GameRepository
import javax.inject.Inject

class GetSearchGamesUseCase @Inject constructor(
    private val repository: GameRepository
) : UseCase<String, Result<List<Game>>>() {
    override suspend fun invoke(params: String): Result<List<Game>> {
        return repository.searchGame(params)
    }
}