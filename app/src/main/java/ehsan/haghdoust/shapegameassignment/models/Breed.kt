package ehsan.haghdoust.shapegameassignment.models

import java.io.Serializable

data class Breed(val name: String, val subBreeds: List<String>): Serializable
