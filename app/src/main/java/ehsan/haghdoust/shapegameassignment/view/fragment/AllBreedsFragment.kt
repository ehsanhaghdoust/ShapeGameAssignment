package ehsan.haghdoust.shapegameassignment.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ehsan.haghdoust.shapegameassignment.R
import ehsan.haghdoust.shapegameassignment.databinding.AllBreedsFragmentLayoutBinding
import ehsan.haghdoust.shapegameassignment.view.adapters.BreedsAdapter
import ehsan.haghdoust.shapegameassignment.viewModel.AllBreedsFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllBreedsFragment: Fragment() {

    lateinit var binding: AllBreedsFragmentLayoutBinding
    lateinit var viewModel: AllBreedsFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = AllBreedsFragmentLayoutBinding.inflate(layoutInflater, container, false)
        doInit()
        setObservers()
        viewModel.getAllBreeds()
        return binding.root
    }

    private fun doInit() {
        viewModel = ViewModelProvider(requireActivity())[AllBreedsFragmentViewModel::class.java]
        binding.breedsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setObservers() {
        viewModel.breeds.observe(viewLifecycleOwner) { it ->
            binding.breedsRecyclerView.adapter = BreedsAdapter(it) { breed ->
                CoroutineScope(Dispatchers.Main).launch {
                    val bundle = bundleOf("breed" to breed)
                    this@AllBreedsFragment.findNavController().navigate(R.id.action_allBreedsFragment_to_breedImagesFragment, bundle)
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            val snackbar = Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}