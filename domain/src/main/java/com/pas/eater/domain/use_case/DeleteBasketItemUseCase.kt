package com.pas.eater.domain.use_case

import com.pas.eater.domain.repository.BasketRepository

class DeleteBasketItemUseCase(
    private val repo: BasketRepository,
) {
    suspend operator fun invoke(id: Int) {
        repo.deleteBasketItem(id)
    }
}
