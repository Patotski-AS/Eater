package com.pas.eater.data.mappers

import com.pas.eater.data.data_sourse.db.entity.BasketItemEntity
import com.pas.eater.domain.models.BasketItem
import com.pas.eater.domain.util.Mapper

data class MapBasket(
    val itemToEntity: MapBasketItemToBasketItemEntity,
    val entityToItem: MapBasketItemEntityToBasketItem,
)

class MapBasketItemToBasketItemEntity: Mapper<BasketItem, BasketItemEntity> {
    override fun map(from: BasketItem): BasketItemEntity {
        return BasketItemEntity(
            id = from.id,
            imageUrl = from.imageUrl,
            name = from.name,
            price = from.price,
            weight = from.weight,
            count = from.count
        )
    }
}

class MapBasketItemEntityToBasketItem: Mapper<BasketItemEntity, BasketItem> {
    override fun map(from: BasketItemEntity): BasketItem {
        return BasketItem(
            id = from.id,
            imageUrl = from.imageUrl,
            name = from.name,
            price = from.price,
            weight = from.weight,
            count = from.count
        )
    }
}
