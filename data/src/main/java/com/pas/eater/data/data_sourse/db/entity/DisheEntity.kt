package com.pas.eater.data.data_sourse.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val DISHE_TABLE = "dishes_table"

@Entity(tableName = DISHE_TABLE)
data class DisheEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("image_url") val imageUrl: String?,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("tegs") val tegs: List<String>,
    @ColumnInfo("weight") val weight: Int)
