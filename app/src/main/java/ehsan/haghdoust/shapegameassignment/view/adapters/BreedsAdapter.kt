package ehsan.haghdoust.shapegameassignment.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import ehsan.haghdoust.shapegameassignment.R
import ehsan.haghdoust.shapegameassignment.databinding.BreedAdapterViewholderLayoutBinding
import ehsan.haghdoust.shapegameassignment.models.Breed
import ehsan.haghdoust.shapegameassignment.utility.ApplicationClass
import ehsan.haghdoust.shapegameassignment.utility.capitalized
import ehsan.haghdoust.shapegameassignment.view.adapters.viewHolders.BreedAdapterViewHolder
import java.util.*

class BreedsAdapter(var breeds: List<Breed>, val onItemClicked: (breed: Breed) -> Unit) : RecyclerView.Adapter<BreedAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedAdapterViewHolder {
        val binding = BreedAdapterViewholderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedAdapterViewHolder, position: Int) {
        if ((position % 2) == 0) {
            holder.binding.root.setCardBackgroundColor(AppCompatResources.getColorStateList(ApplicationClass.applicationContext(), R.color.grey_100))
        } else {
            holder.binding.root.setCardBackgroundColor(AppCompatResources.getColorStateList(ApplicationClass.applicationContext(), R.color.white))
        }
        holder.binding.breedNameTextView.text = breeds[position].name.capitalized()
        holder.binding.root.isClickable = true
        holder.binding.root.setOnClickListener {
            onItemClicked(breeds[position])
        }
    }

    override fun getItemCount(): Int {
        return breeds.size
    }
}