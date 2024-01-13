package food

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import snapbite.app.food.data.SqlDelightFoodDataSource
import snapbite.app.food.domain.Food
import snapbite.app.food.domain.FoodRepository
import snapbite.app.food.ui.FoodListEvent
import snapbite.app.food.ui.FoodListViewModel
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class FoodUnitTest {

    @MockK
    private lateinit var mockKFoodDataSource: SqlDelightFoodDataSource

    @MockK
    private lateinit var mockKFoodRepository: FoodRepository

    @InjectMockKs
    private lateinit var mockKFoodListViewModel: FoodListViewModel

    private val testFood = Food(
        foodId = 1,
        foodName = "Test Food #1",
        foodCaption = "This is Test Food #1",
        foodImage = null,
        foodDate = Clock.System.now().toEpochMilliseconds(),
        foodEmoji = "\uD83D\uDE0B"
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockKFoodListViewModel = FoodListViewModel(
            foodRepository = mockKFoodRepository,
            foodDataSource = mockKFoodDataSource
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test to check if food list has been updated after saving a food item`() = runTest {
        mockKFoodDataSource.insertFood(food = testFood)
        advanceUntilIdle()
        mockKFoodDataSource.getFoods().collectLatest {
            assertEquals(expected = listOf(testFood), actual = it)
        }
    }

}