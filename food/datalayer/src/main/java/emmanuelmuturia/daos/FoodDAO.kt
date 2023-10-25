package emmanuelmuturia.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emmanuelmuturia.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity)

    @Query(value = "SELECT * FROM snapBiteFoods WHERE dayId = :dayId")
    fun getAllFoods(dayId: Int): Flow<List<FoodEntity>>

    @Query(value = "SELECT * FROM snapbiteFoods WHERE foodId = :foodId")
    suspend fun getFoodById(foodId: Int): FoodEntity

}