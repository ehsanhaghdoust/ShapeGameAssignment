package ehsan.haghdoust.shapegameassignment.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ehsan.haghdoust.shapegameassignment.repository.database.entity.LikedImage

@Dao
interface DaoAccess {

    @Insert
    fun saveLikedImage(likedImage: LikedImage)

    @Query("select * from liked_image where url=:url")
    fun getSavedUrl(url: String): LikedImage?

    @Delete
    fun deleteLikedImage(likedImage: LikedImage)

    @Query("select * from liked_image where breed=:breed")
    fun getBreedImages(breed: String): List<LikedImage>


    @Query("SELECT DISTINCT breed from liked_image ")
    fun getBreedNames(): List<String>?

    @Query("select * from liked_image")
    fun getAll(): List<LikedImage>
}