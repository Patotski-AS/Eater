package com.pas.eater.data.data_sourse.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CATEGORY_TABLE = "category_table"

@Entity(tableName = CATEGORY_TABLE)
data class СategoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("image_url") val imageUrl: String,
    @ColumnInfo("name") val name: String)
