package ehsan.haghdoust.shapegameassignment.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BreedInfo(val name: String, val data: List<String>?): Serializable