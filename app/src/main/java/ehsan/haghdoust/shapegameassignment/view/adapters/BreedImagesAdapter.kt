package ehsan.haghdoust.shapegameassignment.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ehsan.haghdoust.shapegameassignment.R
import ehsan.haghdoust.shapegameassignment.databinding.BreedImageAdapterViewHolderLayoutBinding
import ehsan.haghdoust.shapegameassignment.models.Breed
import ehsan.haghdoust.shapegameassignment.repository.database.entity.LikedImage
import ehsan.haghdoust.shapegameassignment.utility.ApplicationClass
import ehsan.haghdoust.shapegameassignment.view.adapters.viewHolders.BreedImagesAdapterViewHolder
import kotlinx.coroutines.*

class BreedImagesAdapter(val breed: Breed?, var imageUrls: List<String>, val isClickable: Boolean = true) :
    RecyclerView.Adapter<BreedImagesAdapterViewHolder>() {

    val databse = ApplicationClass.database

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImagesAdapterViewHolder {
        val binding = BreedImageAdapterViewHolderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedImagesAdapterViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: BreedImagesAdapterViewHolder, position: Int) {

        var isLiked = MutableLiveData<LikedImage?>()
        Picasso.get()
                .load(imageUrls[position])
                .placeholder(R.drawable.puppy_placeholder_image)
                .error(R.drawable.user_placeholder_error)
                .into(holder.binding.imageView)

        if(isClickable) {
            holder.binding.likeIconImageView.setOnTouchListener { view, motionEvent ->
                when (holder.binding.likeIconImageView.drawable.constantState) {
                    AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                            R.drawable.heart_icon_gray)?.constantState -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            databse?.dao?.saveLikedImage(likedImage = LikedImage(breed = breed!!.name, url = imageUrls[position]))
                        }
                        holder.binding.likeIconImageView.setImageDrawable(AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                                R.drawable.heart_icon_red))
                    }

                    AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                            R.drawable.heart_icon_red)?.constantState -> {
                        CoroutineScope(Dispatchers.IO).launch {
                            databse?.dao?.deleteLikedImage(likedImage = LikedImage(breed = breed!!.name, url = imageUrls[position]))
                        }
                        holder.binding.likeIconImageView.setImageDrawable(AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                                R.drawable.heart_icon_gray))
                    }
                }
                return@setOnTouchListener false
            }
        } else {
            holder.binding.likeIconImageView.isEnabled = false
            holder.binding.likeIconImageView.visibility = View.GONE
        }


        runBlocking {
            withContext(Dispatchers.IO) {
                databse.let {
                    isLiked.postValue(it?.dao?.getSavedUrl(url = imageUrls[position]))
                }
            }
        }

        isLiked.observe(holder.binding.root.context as LifecycleOwner) {
            if (it == null) {
                holder.binding.likeIconImageView.setImageDrawable(AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                        R.drawable.heart_icon_gray))
            } else {
                holder.binding.likeIconImageView.setImageDrawable(AppCompatResources.getDrawable(ApplicationClass.applicationContext(),
                        R.drawable.heart_icon_red))
            }
        }

    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    fun clearup() {
        imageUrls = emptyList()
    }
}