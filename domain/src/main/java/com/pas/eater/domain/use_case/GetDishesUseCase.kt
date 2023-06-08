package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.DishesRepository

class GetDishesUseCase(
    private val repo: DishesRepository,
) {
    operator fun invoke() = repo.getDishesFromDB()
}
