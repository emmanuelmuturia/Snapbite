package snapbite.app.about.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import snapbite.app.BuildConfig
import snapbite.app.about.domain.AboutRepository
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

            }
        }
    }

    fun getAppVersion(context: Context): String {
        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }

        return packageInfo?.versionName ?: "Unknown Version"
    }

}