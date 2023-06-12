package com.pas.eater.data.mappers

import com.pas.eater.data.data_sourse.api.pojo.СategoryPojo
import com.pas.eater.data.data_sourse.db.entity.СategoryEntity
import com.pas.eater.domain.models.Category
import com.pas.eater.domain.util.Mapper

data class MapCategory(
    val categoryPojoToCategory: MapCategoryPojoToCategory,
    val categoryToCategoryEntity: MapCategoryToCategoryEntity,
    val categoryEntityToCategory: MapCategoryEntityToCategory,
)


class MapCategoryPojoToCategory : Mapper<СategoryPojo, Category> {
    override fun map(from: СategoryPojo): Category {
        return Category(
            id = from.id, imageUrl = from.imageUrl, name = from.name
        )
    }
}

class MapCategoryToCategoryEntity : Mapper<Category, СategoryEntity> {
    override fun map(from: Category): СategoryEntity {
        return СategoryEntity(
            id = from.id, imageUrl = from.imageUrl, name = from.name
        )
    }
}

class MapCategoryEntityToCategory : Mapper<СategoryEntity, Category> {
    override fun map(from: СategoryEntity): Category {
        return Category(
            id = from.id, imageUrl = from.imageUrl, name = from.name
        )
    }
}
