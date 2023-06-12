package com.pas.eater.domain.use_case

import com.pas.eater.domain.models.BasketItem
import com.pas.eater.domain.repository.BasketRepository

class InsertBasketItemUseCase(
    private val repo: BasketRepository,
) {
    suspend operator fun invoke(item: BasketItem) {
        repo.insertBasketItem(item)
    }
}
