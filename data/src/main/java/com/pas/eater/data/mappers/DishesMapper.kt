package com.pas.eater.data.mappers

import com.pas.eater.data.data_sourse.api.pojo.DishePojo
import com.pas.eater.data.data_sourse.db.entity.DisheEntity
import com.pas.eater.domain.models.Dish
import com.pas.eater.domain.util.Mapper

data class MapDishes(
    val dishesPojoToDish: MapDishesPojoToDish,
    val dishToDisheEntity: MapDishToDisheEntity,
    val disheEntityToDish: MapDisheEntityToDish,
)

class MapDishesPojoToDish : Mapper<DishePojo, Dish> {
    override fun map(from: DishePojo): Dish {
        return Dish(
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

class MapDishToDisheEntity : Mapper<Dish, DisheEntity> {
    override fun map(from: Dish): DisheEntity {
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

class MapDisheEntityToDish : Mapper<DisheEntity, Dish> {
    override fun map(from: DisheEntity): Dish {
        return Dish(
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
