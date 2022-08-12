package ehsan.haghdoust.shapegameassignment.models.response

data class BaseResponse<T>(val message: T, val status: String)