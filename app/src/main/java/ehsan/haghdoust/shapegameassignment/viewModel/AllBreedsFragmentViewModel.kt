package ehsan.haghdoust.shapegameassignment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import ehsan.haghdoust.shapegameassignment.models.Breed
import ehsan.haghdoust.shapegameassignment.repository.network.ApiClient
import ehsan.haghdoust.shapegameassignment.repository.network.ApiInterface
import ehsan.haghdoust.shapegameassignment.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AllBreedsFragmentViewModel : BaseViewModel() {

//    private val viewModelJob = Job()
//    val networkScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val breeds = MutableLiveData<List<Breed>>()
    var error = MutableLiveData<Error>()

    init {

    }

    fun getAllBreeds() {
        val client = ApiClient().getApiClient().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = client.getAllBreeds()
            if (response.isSuccessful && response.code() == 200) {
                val breadsJsonObject = Gson().fromJson(response.body()!!.message, JsonObject::class.java)
                val keys = breadsJsonObject.keySet()
                var breedsTemp = mutableListOf<Breed>()
                keys.forEach { it ->
                    val temp = Breed(name = it, subBreeds = breadsJsonObject[it].asJsonArray.map { j -> j.asString })
                    breedsTemp.add(temp)
                }
                breeds.postValue(breedsTemp)

            } else {

            }

            try {
                if (response.isSuccessful) {

                } else {
                    error.postValue(Error("Some webservice problems happened"))
                }
            } catch (e: HttpException) {
                Log.e("HttpException", "Something HTTP stuff went wrong")
                error.postValue(Error("Something HTTP stuff went wrong"))
            } catch (e: Throwable) {
                Log.e("Exception", "Something non-HTTP stuff went wrong")
                error.postValue(Error("Something non-HTTP stuff went wrong"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}