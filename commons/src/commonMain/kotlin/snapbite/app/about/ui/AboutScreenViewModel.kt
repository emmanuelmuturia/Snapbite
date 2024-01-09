package snapbite.app.about.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import snapbite.app.BuildConfig
import timber.log.Timber
import java.io.IOException

class AboutScreenViewModel(
    //private val aboutRepository: AboutRepository
) : ViewModel() {

    fun getPrivacyPolicy(context: Context) {
        viewModelScope.launch {
            val privacyPolicyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.privacyPolicy))

            try {
                privacyPolicyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(privacyPolicyIntent)
            } catch (e: IOException) {
                Timber.tag(tag = "Privacy Policy Exception")
                    .e(message = "Failed to get the Privacy Policy due to: %s", e.printStackTrace())
            }
        }
    }

    fun getTermsAndConditions(context: Context) {
        viewModelScope.launch {
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
    }

    fun getAppVersion(context: Context): String {
        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.tag(tag = "App Version Exception")
                .e(message = "Failed to get the App Version due to: %s", e.printStackTrace())
            null
        }

        return packageInfo?.versionName ?: "Unknown Version"
    }

}