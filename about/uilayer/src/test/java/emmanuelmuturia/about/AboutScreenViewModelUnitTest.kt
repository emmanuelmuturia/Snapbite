package emmanuelmuturia.about

import android.app.Application
import android.content.Context
import emmanuelmuturia.about.repository.AboutRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(value = RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class AboutScreenViewModelUnitTest {

    @MockK
    private lateinit var mockKContext: Context

    @MockK
    private lateinit var mockKApplication: Application

    @MockK
    private lateinit var mockKAboutRepository: AboutRepository

    @InjectMockKs
    private lateinit var mockKAboutScreenViewModel: AboutScreenViewModel

    @Before
    fun setup() {

        MockKAnnotations.init(this)

        every { mockKAboutRepository.getAppVersion(context = mockKContext) } returns "1.00"

        mockKAboutScreenViewModel = AboutScreenViewModel(
            application = mockKApplication,
            aboutRepository = mockKAboutRepository
        )

    }

    @Test
    fun `Test to check if appVersion() returns the correct App Version`() {

        val expectedVersion = "1.00"

        val actualAppVersion = mockKAboutScreenViewModel.getAppVersion(context = mockKContext)

        assertEquals(expectedVersion, actualAppVersion)

    }

}