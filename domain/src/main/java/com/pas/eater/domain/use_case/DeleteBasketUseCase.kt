package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.BasketRepository

class DeleteBasketUseCase(
    private val repo: BasketRepository,
) {
    suspend operator fun invoke() {
        repo.deleteBasket()
    }
}
