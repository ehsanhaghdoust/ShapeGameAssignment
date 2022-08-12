package ehsan.haghdoust.shapegameassignment.viewModel

import androidx.lifecycle.MutableLiveData
import ehsan.haghdoust.shapegameassignment.repository.network.ApiClient
import ehsan.haghdoust.shapegameassignment.repository.network.ApiInterface
import ehsan.haghdoust.shapegameassignment.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BreedImagesFragmentViewModel: BaseViewModel() {

    var images = MutableLiveData<List<String>?>()

    fun getAllImagesForBreed(breedName: String) {
        val client = ApiClient().getApiClient().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = client.getBreedImages(breedName = breedName)
            if (response.isSuccessful && response.code() == 200) {
                images.postValue(response.body()!!.message)
            } else {

            }
        }
    }

    override fun onCleared() {
        images.value = emptyList()
        super.onCleared()
    }

    fun cleanDataSet() {
        images.value = emptyList()
    }

}