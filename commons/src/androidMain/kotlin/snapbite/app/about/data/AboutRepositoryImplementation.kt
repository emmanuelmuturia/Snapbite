package snapbite.app.about.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import snapbite.app.BuildConfig
import snapbite.app.about.domain.AboutRepository
import timber.log.Timber

class AboutRepositoryImplementation(
    private val context: Context
) : AboutRepository {

    override suspend fun getPrivacyPolicy() {
        val privacyPolicyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.privacyPolicy))
        try {
            privacyPolicyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(privacyPolicyIntent)
        } catch (e: Exception) {
            Timber.tag(tag = "Privacy Policy Exception")
                .e(message = "Failed to get the Privacy Policy due to: %s", e.message)
        }
    }

    override suspend fun getTermsAndConditions() {
        val termsAndConditionsIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.termsAndConditions))
        try {
            termsAndConditionsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(termsAndConditionsIntent)
        } catch (e: Exception) {
            Timber.tag(tag = "Terms & Conditions Exception")
                .e(message = "Failed to get the Terms & Conditions due to: %s", e.message)
        }
    }

    override fun getAppVersion(): String {
        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: Exception) {
            Timber.tag(tag = "App Version Exception")
                .e(message = "Failed to get the App Version due to: %s", e.message)
            null
        }
        return packageInfo?.versionName ?: "Unknown Version"
    }

}