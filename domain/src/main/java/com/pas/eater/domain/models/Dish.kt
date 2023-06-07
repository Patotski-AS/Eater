package com.pas.eater.domain.models

data class Dish(
    val description: String,
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)
