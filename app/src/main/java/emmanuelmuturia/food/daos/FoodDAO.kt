package emmanuelmuturia.food.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodEntity: FoodEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFood(foodEntity: FoodEntity)

    @Query(value = "SELECT * FROM snapBiteFoods")
    fun getAllFoods(): Flow<List<FoodEntity>>

    @Query(value = "SELECT * FROM snapbiteFoods WHERE foodId = :foodId")
    suspend fun getFoodById(foodId: Int): FoodEntity

}