package ehsan.haghdoust.shapegameassignment.repository.network

import com.google.gson.JsonObject
import ehsan.haghdoust.shapegameassignment.models.response.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<BaseResponse<JsonObject>>

    @GET("breed/{breedName}/images")
    suspend fun getBreedImages(@Path("breedName")  breedName: String) : Response<BaseResponse<List<String>?>>
}