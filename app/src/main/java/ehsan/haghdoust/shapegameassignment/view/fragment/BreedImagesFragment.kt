package ehsan.haghdoust.shapegameassignment.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import ehsan.haghdoust.shapegameassignment.databinding.BreedImagesFragmentLayoutBinding
import ehsan.haghdoust.shapegameassignment.models.Breed
import ehsan.haghdoust.shapegameassignment.utility.capitalized
import ehsan.haghdoust.shapegameassignment.view.adapters.BreedImagesAdapter
import ehsan.haghdoust.shapegameassignment.viewModel.BreedImagesFragmentViewModel

class BreedImagesFragment : Fragment() {

    private lateinit var binding: BreedImagesFragmentLayoutBinding
    private lateinit var viewModel: BreedImagesFragmentViewModel
    private lateinit var breed: Breed

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BreedImagesFragmentLayoutBinding.inflate(layoutInflater, container, false)

        doInit()
        setObservers()
        viewModel.getAllImagesForBreed(breedName = breed.name)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroy() {
        (binding.breedImagesRecyclerView.adapter as BreedImagesAdapter).clearup()
        (binding.breedImagesRecyclerView.adapter as BreedImagesAdapter).notifyDataSetChanged()
        viewModel.cleanDataSet()
        super.onDestroy()
    }

    private fun fillFields() {
        binding.breedNameTextView.text = breed.name.capitalized()
    }


    private fun doInit() {
        viewModel = ViewModelProvider(requireActivity())[BreedImagesFragmentViewModel::class.java]

        arguments?.let {
            try {
                breed = requireArguments().get("breed") as Breed
                fillFields()
                binding.breedImagesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
            } catch (e: Exception) {
            }
        } ?: run {

        }
    }

    private fun setObservers() {
        viewModel.images.observe(viewLifecycleOwner) {
            it.let {
                binding.breedImagesRecyclerView.adapter = BreedImagesAdapter(breed = breed, imageUrls = it!!)
            }
        }
    }
}