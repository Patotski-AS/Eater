package com.pas.eater.data.data_sourse.api.pojo

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("сategories") val сategories: List<СategoryPojo>)

data class СategoryPojo(
    @SerializedName("id") val id: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("name") val name: String)
