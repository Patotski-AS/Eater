package com.pas.eater.data.data_sourse.api.pojo


import com.google.gson.annotations.SerializedName

data class DisheResponse(
    @SerializedName("dishes") val dishes: List<DishePojo>)

data class DishePojo(
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("tegs") val tegs: List<String>,
    @SerializedName("weight") val weight: Int)
