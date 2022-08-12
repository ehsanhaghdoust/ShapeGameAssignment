package ehsan.haghdoust.shapegameassignment.viewModel

import androidx.lifecycle.MutableLiveData
import ehsan.haghdoust.shapegameassignment.repository.database.entity.LikedImage
import ehsan.haghdoust.shapegameassignment.utility.ApplicationClass
import ehsan.haghdoust.shapegameassignment.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.*

class FavoritesFragmentViewModel : BaseViewModel() {

    var listOfBreeds = MutableLiveData<List<String>?>()
    var listOfBreedImagesUrls = MutableLiveData<List<LikedImage>?>()

    val databse = ApplicationClass.database

    fun getListOfBreedsInDatabase() {
        coroutineIO.launch {
            var names = databse?.dao?.getBreedNames()?.toMutableList()
            names?.add(0, "all")
            listOfBreeds.postValue(names)
        }
    }

    fun getListOfLikedImagesBaseOnBreed(breed: String) {
        coroutineIO.launch {
            listOfBreedImagesUrls.postValue(databse?.dao?.getBreedImages(breed = breed))
        }
    }

    fun getAllLikedImages() {
        coroutineIO.launch {
            listOfBreedImagesUrls.postValue(databse?.dao?.getAll())
        }
    }
}