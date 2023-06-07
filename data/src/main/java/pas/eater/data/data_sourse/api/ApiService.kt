package pas.eater.data.data_sourse.api

import pas.eater.data.data_sourse.api.pojo.CategoriesResponse
import pas.eater.data.data_sourse.api.pojo.DisheResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/v3/c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): DisheResponse

    @GET("/v3/058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategory(): CategoriesResponse

}