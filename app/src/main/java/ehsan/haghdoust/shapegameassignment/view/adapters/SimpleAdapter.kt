package ehsan.haghdoust.shapegameassignment.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ehsan.haghdoust.shapegameassignment.databinding.SimpleAdapterViewHolderLayoutBinding
import ehsan.haghdoust.shapegameassignment.utility.capitalized
import ehsan.haghdoust.shapegameassignment.view.adapters.viewHolders.SimpleAdapterViewHolder

class SimpleAdapter(var breeds: List<String>, val onItemClicked: (breed: String) -> Unit) :
    RecyclerView.Adapter<SimpleAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleAdapterViewHolder {
        val binding = SimpleAdapterViewHolderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpleAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleAdapterViewHolder, position: Int) {
        holder.binding.breedNameTextView.text = breeds[position].capitalized()

        holder.binding.root.setOnClickListener {
            onItemClicked(breeds[position])
        }
    }

    override fun getItemCount(): Int {
        return breeds.size
    }
}