package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.BasketRepository

class GetBasketUseCase(
    private val repo: BasketRepository,
) {
    operator fun invoke() = repo.getBasketFromDB()
}
