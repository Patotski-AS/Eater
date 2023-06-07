package com.pas.eater.data.mappers

import com.pas.eater.data.data_sourse.api.pojo.СategoryPojo
import com.pas.eater.data.data_sourse.db.entity.СategoryEntity
import com.pas.eater.domain.util.Mapper

class MapCategoryPojoToCategoryEntity: Mapper<СategoryPojo, СategoryEntity> {
    override fun map(from: СategoryPojo): СategoryEntity {
        return СategoryEntity(
            id = from.id, imageUrl = from.imageUrl, name = from.name
        )
    }
}
