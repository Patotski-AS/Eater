package com.pas.eater.domain.models

data class BasketItem(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val price: Int,
    val weight: Int,
    val count: Int,
)
