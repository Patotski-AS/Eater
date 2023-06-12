package com.pas.eater.presentation.basket.adapter

import com.pas.eater.presentation.util.EaterItem

data class BasketEaterItem(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val price: Int,
    val weight: Int,
    val count: Int,
): EaterItem {
    override val itemId: Int
        get() = id
}