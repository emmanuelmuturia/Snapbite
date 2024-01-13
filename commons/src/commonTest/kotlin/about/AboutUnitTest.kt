package about

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import snapbite.app.about.data.AboutRepositoryImplementation
import snapbite.app.about.domain.AboutRepository
import snapbite.app.about.ui.AboutScreenViewModel
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class AboutUnitTest {

    @MockK(relaxed = true)
    private lateinit var mockKAboutRepository: AboutRepository

    @InjectMockKs
    private lateinit var mockKAboutScreenViewModel: AboutScreenViewModel

    private val appVersion = "1.00"

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        coEvery { mockKAboutRepository.getAppVersion() } returns appVersion
        mockKAboutScreenViewModel = AboutScreenViewModel(
            aboutRepository = mockKAboutRepository
        )
    }

    @Test
    fun `Test to confirm if the app version is correct`() {
        assertEquals(expected = mockKAboutScreenViewModel.getAppVersion(), actual = "1.00")
    }

}