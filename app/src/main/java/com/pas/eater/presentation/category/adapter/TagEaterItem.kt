package com.pas.eater.presentation.category.adapter

import com.pas.eater.presentation.util.EaterItem

data class TagEaterItem(val id: Int, val tag: String, var onSelect: Boolean): EaterItem {
    override val itemId: Int
        get() = id
}
