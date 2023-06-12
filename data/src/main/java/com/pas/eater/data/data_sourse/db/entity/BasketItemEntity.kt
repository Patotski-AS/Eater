package com.pas.eater.data.data_sourse.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val BASKET_TABLE = "basket_table"

@Entity(tableName = BASKET_TABLE)
data class BasketItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("image_url") val imageUrl: String?,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("count") val count: Int,
)
