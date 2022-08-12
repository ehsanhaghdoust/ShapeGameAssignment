package ehsan.haghdoust.shapegameassignment.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ehsan.haghdoust.shapegameassignment.R
import ehsan.haghdoust.shapegameassignment.databinding.BottomSheetLayoutBinding
import ehsan.haghdoust.shapegameassignment.view.adapters.SimpleAdapter

class BottomSheetDialog(private val breeds: List<String>,
                        private val onItemClicked: (breed: String) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = BottomSheetLayoutBinding.inflate(layoutInflater, container, false)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogStyle)
        binding.breedsNamesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.breedsNamesRecyclerView.adapter = SimpleAdapter(breeds) {
            onItemClicked(it)
        }
        return binding.root
    }
}