package food

import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.flow.flow
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

    private val testFoodList = mutableListOf<Food>(
        Food(
            foodId = 1L,
            foodName = "Test Food #1",
            foodCaption = "This is Test Food #1",
            foodImage = null,
            foodDate = Clock.System.now().toEpochMilliseconds(),
            foodEmoji = "\uD83D\uDE0B"
        ),
        Food(
            foodId = 2L,
            foodName = "Test Food #2",
            foodCaption = "This is Test Food #2",
            foodImage = null,
            foodDate = Clock.System.now().toEpochMilliseconds(),
            foodEmoji = "\uD83D\uDE0B"
        ),
        Food(
            foodId = 3L,
            foodName = "Test Food #3",
            foodCaption = "This is Test Food #3",
            foodImage = null,
            foodDate = Clock.System.now().toEpochMilliseconds(),
            foodEmoji = "\uD83D\uDE0B"
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        coEvery { mockKFoodDataSource.getFoods() } coAnswers { flow { emit(value = testFoodList) } }
        mockKFoodListViewModel = FoodListViewModel(
            foodRepository = mockKFoodRepository,
            foodDataSource = mockKFoodDataSource
        )
    }

    @Test
    fun `Test to check if a food item is deleted the database`() = runTest {
        val foodList = mockKFoodListViewModel.foods.value
        mockKFoodListViewModel.onEvent(event = FoodListEvent.DeleteFood)
        assertEquals(expected = 2, foodList.size)
    }

}