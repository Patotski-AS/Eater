package com.pas.eater.data.mappers

import com.pas.eater.data.data_sourse.api.pojo.DishePojo
import com.pas.eater.data.data_sourse.db.entity.DisheEntity
import com.pas.eater.domain.util.Mapper

class MapDishesPojoToDishesEntity: Mapper<DishePojo, DisheEntity> {
    override fun map(from: DishePojo): DisheEntity {
        return DisheEntity(
            id = from.id,
            description = from.description,
            imageUrl = from.imageUrl,
            name = from.name,
            price = from.price,
            tegs = from.tegs,
            weight = from.weight
        )
    }
}
