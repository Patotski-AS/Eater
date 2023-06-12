package com.pas.eater.presentation.home.adapter

import com.pas.eater.presentation.util.EaterItem

data class CategoryEaterItem(
    val id: Int,
    val imageUrl: String,
    val name: String
) : EaterItem {
    override val itemId: Int
        get() = id
}