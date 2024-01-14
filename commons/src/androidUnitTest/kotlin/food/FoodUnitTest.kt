package food

import android.content.Context
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import snapbite.app.core.data.DatabaseDriverFactory
import snapbite.app.database.FoodEntity
import snapbite.app.database.FoodQueries
import snapbite.app.database.SnapbiteDatabase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class FoodUnitTest {

    @MockK
    private lateinit var foodQueries: FoodQueries

    @BeforeTest
    fun setup() {
        val mockKContext: Context = mockk()
        val driver =
            DatabaseDriverFactory(context = mockKContext, inMemory = true).createSnapbiteDatabase()
        val database = SnapbiteDatabase(driver = driver)
        foodQueries = database.foodQueries
    }

    @Test
    fun `Test to confirm that getFoods() works as expected`() {

        val foodList = listOf(
            FoodEntity(
                foodId = 1L,
                foodName = "Test Food #1",
                foodCaption = "This is Test Food #1",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            ),
            FoodEntity(
                foodId = 2L,
                foodName = "Test Food #2",
                foodCaption = "This is Test Food #2",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            ),
            FoodEntity(
                foodId = 3L,
                foodName = "Test Food #3",
                foodCaption = "This is Test Food #3",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            )
        )

        foodQueries.transaction {
            foodList.forEach { food ->
                foodQueries.insertFoodItem(
                    foodId = food.foodId,
                    foodName = food.foodName,
                    foodCaption = food.foodCaption,
                    foodEmoji = food.foodEmoji,
                    foodImage = null,
                    foodDate = 7L
                )
            }
        }

        val result: List<FoodEntity> = foodQueries.getFoods().executeAsList()

        val sortedExpected = foodList.sortedBy { it.foodId }
        val sortedResult = result.sortedBy { it.foodId }

        assertEquals(expected = sortedExpected, actual = sortedResult)
    }

    @Test
    fun `Test to confirm if deleteFood() works as expected`() {

        val foodList = mutableListOf(
            FoodEntity(
                foodId = 1L,
                foodName = "Test Food #1",
                foodCaption = "This is Test Food #1",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            ),
            FoodEntity(
                foodId = 2L,
                foodName = "Test Food #2",
                foodCaption = "This is Test Food #2",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            ),
            FoodEntity(
                foodId = 3L,
                foodName = "Test Food #3",
                foodCaption = "This is Test Food #3",
                foodImage = null,
                foodDate = 7L,
                foodEmoji = "\uD83D\uDE0B"
            )
        )

        foodQueries.transaction {
            foodList.forEach { food ->
                foodQueries.insertFoodItem(
                    foodId = food.foodId,
                    foodName = food.foodName,
                    foodCaption = food.foodCaption,
                    foodEmoji = food.foodEmoji,
                    foodImage = null,
                    foodDate = 7L
                )
            }
        }

        foodQueries.deleteFood(foodId = 2L)

        val resultAfterDeletion: List<FoodEntity> = foodQueries.getFoods().executeAsList()

        assertEquals(expected = 2, resultAfterDeletion.size)

    }

}