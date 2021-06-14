package com.abdhilabs.gamelabs.favourite.domain

import com.abdhilabs.coreandroid.abstraction.UseCase
import com.abdhilabs.coreandroid.db.entity.GameFavorite
import com.abdhilabs.gamelabs.favourite.data.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
): UseCase<UseCase.None, Flow<List<GameFavorite>>>(){
    override suspend fun invoke(params: None): Flow<List<GameFavorite>> {
        return repository.getAllFavoriteGame()
    }
}