package pas.eater.data.data_sourse.api.pojo


import com.google.gson.annotations.SerializedName


data class CategoriesResponse(
    @SerializedName("сategories") val сategories: List<Category>)

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("name") val name: String)