package settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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
import snapbite.app.settings.domain.SettingsRepository
import snapbite.app.settings.ui.SettingsScreenViewModel
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE)
class SettingsUnitTest {

    @MockK
    private lateinit var mockKContext: Context

    @MockK
    private lateinit var mockKSettingsRepository: SettingsRepository

    @InjectMockKs
    private lateinit var mockKSettingsScreenViewModel: SettingsScreenViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        mockKSettingsScreenViewModel = SettingsScreenViewModel(
            settingsRepository = mockKSettingsRepository
        )
    }

    @Test
    fun `Test to confirm that Rate Us is correctly invoked`() = runTest {
        val expectedRateUsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.github))
        coEvery { mockKSettingsRepository.rateUs() } returns Unit
        coEvery { mockKContext.startActivity(expectedRateUsIntent) } returns Unit
        assertEquals(expected = mockKContext.startActivity(expectedRateUsIntent), actual = mockKSettingsScreenViewModel.rateUs())
    }

    @Test
    fun `Test to confirm that NavigateToNotificationsSettings is correctly invoked`() = runTest {
        val expectedNotificationsSettingsIntent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            expectedNotificationsSettingsIntent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            expectedNotificationsSettingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE, mockKContext.packageName)
        } else {
            expectedNotificationsSettingsIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            expectedNotificationsSettingsIntent.addCategory(Intent.CATEGORY_DEFAULT)
            expectedNotificationsSettingsIntent.data = Uri.parse("package:" + mockKContext.packageName)
        }
        coEvery { mockKSettingsRepository.navigateToNotificationsSettings() } returns Unit
        coEvery { mockKContext.startActivity(expectedNotificationsSettingsIntent) } returns Unit
        assertEquals(expected = mockKContext.startActivity(expectedNotificationsSettingsIntent), actual = mockKSettingsScreenViewModel.navigateToNotificationsSettings())
    }

}