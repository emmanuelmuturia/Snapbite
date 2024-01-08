package snapbite.app.about.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import snapbite.app.about.domain.AboutRepository
import java.io.IOException

actual class AboutRepositoryImplementation : AboutRepository {

    override suspend fun getPrivacyPolicy(context: Context) {

        val privacyPolicyIntent = Intent(Intent.ACTION_VIEW, Uri.parse("BuildConfig.privacyPolicy"))

        try {
            privacyPolicyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(privacyPolicyIntent)
        } catch (e: IOException) {

        }

    }

    override suspend fun getTermsAndConditions(context: Context) {

        val termsAndConditionsIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("BuildConfig.termsAndConditions"))

        try {
            termsAndConditionsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(termsAndConditionsIntent)
        } catch (e: IOException) {

        }

    }

    override fun getAppVersion(context: Context): String {

        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }

        return packageInfo?.versionName ?: "Unknown Version"

    }

}