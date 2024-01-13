package about

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import snapbite.app.BuildConfig
import snapbite.app.about.domain.AboutRepository
import snapbite.app.about.ui.AboutScreenViewModel
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class AboutUnitTest {

    @MockK
    private lateinit var mockKContext: Context

    @MockK
    private lateinit var mockKAboutRepository: AboutRepository

    @InjectMockKs
    private lateinit var mockKAboutScreenViewModel: AboutScreenViewModel

    private val appVersion = "1.00"

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockKAboutScreenViewModel = AboutScreenViewModel(
            aboutRepository = mockKAboutRepository
        )
    }

    @Test
    fun `Test to confirm that the Privacy Policy intent is correctly invoked`() = runTest {
        val expectedPrivacyPolicyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.privacyPolicy))
        coEvery { mockKAboutRepository.getPrivacyPolicy() } returns Unit
        coEvery { mockKContext.startActivity(expectedPrivacyPolicyIntent) } returns Unit
        assertEquals(expected = mockKContext.startActivity(expectedPrivacyPolicyIntent), actual = mockKAboutScreenViewModel.getPrivacyPolicy())
    }

    @Test
    fun `Test to confirm that the Terms and Conditions intent is correctly invoked`() = runTest {
        val expectedTermsAndConditionsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.termsAndConditions))
        coEvery { mockKAboutRepository.getTermsAndConditions() } returns Unit
        coEvery { mockKContext.startActivity(expectedTermsAndConditionsIntent) } returns Unit
        assertEquals(expected = mockKContext.startActivity(expectedTermsAndConditionsIntent), actual = mockKAboutScreenViewModel.getTermsAndConditions())
    }

    @Test
    fun `Test to confirm if the app version is correct`() {
        coEvery { mockKAboutRepository.getAppVersion() } returns appVersion
        assertEquals(expected = "1.00", actual = mockKAboutScreenViewModel.getAppVersion())
    }

}