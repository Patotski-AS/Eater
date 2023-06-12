package com.pas.eater.presentation.category.adapter

import com.pas.eater.presentation.util.EaterItem

data class DishEaterItem(
    val id: Int,
    val description: String,
    val imageUrl: String?,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int): EaterItem {
    override val itemId: Int
        get() = id
}
