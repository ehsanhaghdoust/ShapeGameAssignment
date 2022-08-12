package ehsan.haghdoust.shapegameassignment.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ehsan.haghdoust.shapegameassignment.R
import ehsan.haghdoust.shapegameassignment.databinding.FavoritesFragmentLayoutBinding
import ehsan.haghdoust.shapegameassignment.models.Breed
import ehsan.haghdoust.shapegameassignment.utility.capitalized
import ehsan.haghdoust.shapegameassignment.view.adapters.BreedImagesAdapter
import ehsan.haghdoust.shapegameassignment.view.dialogs.BottomSheetDialog
import ehsan.haghdoust.shapegameassignment.viewModel.BreedImagesFragmentViewModel
import ehsan.haghdoust.shapegameassignment.viewModel.FavoritesFragmentViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FavoritesFragmentLayoutBinding
    private lateinit var viewModel: FavoritesFragmentViewModel
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FavoritesFragmentLayoutBinding.inflate(layoutInflater, container, false)

        doInit()
        setObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getListOfBreedsInDatabase()

        if (binding.breedNameTextView.text.isNullOrEmpty()) {
            viewModel.getAllLikedImages()
        }

        binding.breedNameTextView.text = resources.getString(R.string.all)
        binding.selectBreedButton.setOnClickListener {

            viewModel.listOfBreeds.value?.let {
                bottomSheetDialog = BottomSheetDialog(breeds = it, onItemClicked = {
                    binding.breedNameTextView.text = it.capitalized()
                    if (it.lowercase() == "all") {
                        viewModel.getAllLikedImages()
                    } else {
                        viewModel.getListOfLikedImagesBaseOnBreed(it)
                    }
                    bottomSheetDialog.dismiss()
                })
                bottomSheetDialog.show(requireActivity().supportFragmentManager, null)
            }
        }
    }

    private fun doInit() {
        viewModel = ViewModelProvider(requireActivity())[FavoritesFragmentViewModel::class.java]
        binding.imagesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun setObservers() {
        viewModel.listOfBreeds.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {

                bottomSheetDialog = BottomSheetDialog(breeds = it, onItemClicked = { breedName ->
                    bottomSheetDialog.dismiss()
                    viewModel.getListOfLikedImagesBaseOnBreed(breed = breedName)
                })
            }
        }

        viewModel.listOfBreedImagesUrls.observe(viewLifecycleOwner) {
            binding.imagesRecyclerView.adapter = BreedImagesAdapter(breed = null, imageUrls = it!!.map { it.url }, isClickable = false)
        }
    }

}