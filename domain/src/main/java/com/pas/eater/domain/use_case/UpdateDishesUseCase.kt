package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.DishesRepository
import com.pas.eater.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateDishesUseCase(
    private val repo: DishesRepository,
) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)

            val newData = repo.getDishesFromApi()
            if(newData.data != null) {
                val newDishes = newData.data
                if(newDishes.isEmpty()) {
                    emit(Resource.Error("No data"))
                } else {
                    repo.insertDishes(newDishes)
                    emit(Resource.Success(true))
                }
            } else {
                emit(Resource.Error(newData.error.toString()))
            }
        }
    }
}
