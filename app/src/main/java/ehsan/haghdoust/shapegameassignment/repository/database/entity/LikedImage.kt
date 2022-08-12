package ehsan.haghdoust.shapegameassignment.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "liked_image", primaryKeys = ["breed", "url"])
data class LikedImage(@NotNull @ColumnInfo(name = "breed") val breed: String,
                      @NotNull @ColumnInfo(name = "url") val url: String) : Serializable