package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.CategoriesRepository
import com.pas.eater.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateCategoriesUseCase(
    private val repo: CategoriesRepository,
) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)

            val newData = repo.getCategoriesFromApi()
            if(newData.data != null) {
                val newCategories = newData.data
                if(newCategories.isEmpty()) {
                    emit(Resource.Error("No data"))
                } else {
                    repo.insertCategories(newData.data)
                    emit(Resource.Success(true))
                }
            } else {
                emit(Resource.Error(newData.error.toString()))
            }
        }
    }
}
