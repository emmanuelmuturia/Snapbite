package emmanuelmuturia.about

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import emmanuelmuturia.about.datalayer.BuildConfig
import emmanuelmuturia.about.datalayer.R
import emmanuelmuturia.about.AboutRepository
import timber.log.Timber
import java.io.IOException

class AboutRepositoryImplementation : emmanuelmuturia.about.AboutRepository {

    override suspend fun getPrivacyPolicy(context: Context) {

        val privacyPolicyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.privacyPolicy))

        try {
            privacyPolicyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(privacyPolicyIntent)
        } catch (e: IOException) {
            Timber.tag(tag = "Privacy Policy Exception")
                .e(message = "Failed to get the Privacy Policy due to: %s", e.printStackTrace())
        }

    }

    override suspend fun getTermsAndConditions(context: Context) {

        val termsAndConditionsIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.termsAndConditions))

        try {
            termsAndConditionsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(termsAndConditionsIntent)
        } catch (e: IOException) {
            Timber.tag(tag = "Terms & Conditions Exception")
                .e(message = "Failed to get the Terms & Conditions due to: %s", e.printStackTrace())
        }

    }

    override fun getAppVersion(context: Context): String {

        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.tag(tag = "App Version Exception")
                .e(message = "Failed to get the App Version due to: %s", e.printStackTrace())
            null
        }

        return packageInfo?.versionName ?: context.getString(R.string.unknown_version)

    }


}