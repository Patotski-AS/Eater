package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.CategoriesRepository

class GetCategoriesUseCase(
    private val repo: CategoriesRepository,
) {
    operator fun invoke() = repo.getCategoriesFromDB()
}
